package com.microsaas.tattoo.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AgendaDao {

	private final Connection connection;

	public AgendaDao(Connection connection) {
		this.connection = connection;
	}

	private static final String INSERIR_HORARIO = "insert into agendamento (prestador_id,data_hora) values (?,?)";
	private static final String OCUPAR_HORARIO = "UPDATE agendamento SET cliente_id = ?, status = 'OCUPADO' WHERE id = ?";
	private static final String DESOCUPAR_HORARIO = "UPDATE agendamento SET cliente_id = null, status = 'DESOCUPADO' WHERE id = ?";
	private static final String VERIFICAR_CONFLITO = "SELECT COUNT(*) FROM agendamento WHERE prestador_id = ? AND data_hora = ?";
	private static final String CONFIRMAR_AGENDA = "UPDATE agendamento SET isAceito = ? WHERE id = ?";
	private static final String DESCONFIRMAR_AGENDA = "UPDATE agendamento SET isAceito = ?, cliente_id = NULL, status = 'DESOCUPADO' WHERE id = ?";
	private static final String INSERIR_HISTORICO = "INSERT INTO historico_agendamento(agendamento_id, prestador_id, data_agendamento, status) VALUES (?, ?, ?, ?)";

	public boolean inserirHorario(String horario, int prestadorId) throws SQLException {
		int rows = 0;

		if (horario != null) {
			try (var checkStmt = connection.prepareStatement(VERIFICAR_CONFLITO)) {
				checkStmt.setInt(1, prestadorId);
				checkStmt.setString(2, horario);

				try (ResultSet rs = checkStmt.executeQuery()) {
					if (rs.next() && rs.getInt(1) > 0) {
						return false;
					}
				}
			}

			int agendamentoId;

			try (var stmt = connection.prepareStatement(INSERIR_HORARIO, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setInt(1, prestadorId);
				stmt.setString(2, horario);
				rows = stmt.executeUpdate();

				try (ResultSet keys = stmt.getGeneratedKeys()) {
					if (keys.next()) {
						agendamentoId = keys.getInt(1);
					} else {
						throw new SQLException("Falha ao obter o ID do agendamento inserido.");
					}
				}
			}

			if (rows > 0) {
				inserirHistorico(agendamentoId, prestadorId, horario, "CRIADO");
			}
		}

		return rows > 0;
	}

	public boolean confirmarAgenda(int horarioId, boolean isAceito) {
		int rows = 0;

		try {
			String novoStatus = isAceito ? "ACEITO" : "CANCELADO";
			String query = isAceito ? CONFIRMAR_AGENDA : DESCONFIRMAR_AGENDA;
			try (var stmt = connection.prepareStatement(query)) {
				stmt.setBoolean(1, isAceito);
				stmt.setInt(2, horarioId);
				rows = stmt.executeUpdate();
			}
				
			System.out.print("novoStatus" + novoStatus + "\n");
			System.out.print("query" + query + "\n");
			if (rows > 0) {
				inserirHistoricoStatusPorId(horarioId, novoStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rows > 0;
	}

	public boolean ocuparHorario(int horarioId, int clienteId) {
		int rows = 0;

		try (var stmt = connection.prepareStatement(OCUPAR_HORARIO)) {
			stmt.setInt(1, clienteId);
			stmt.setInt(2, horarioId);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rows > 0) {
			inserirHistoricoStatusPorId(horarioId, "SOLICITADO");
		}

		return rows > 0;
	}

	public boolean desocuparHorario(int horarioId) {
		int rows = 0;

		try (var stmt = connection.prepareStatement(DESOCUPAR_HORARIO)) {
			stmt.setInt(1, horarioId);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rows > 0) {
			inserirHistoricoStatusPorId(horarioId, "CRIADO");
		}

		return rows > 0;
	}

	private void inserirHistoricoStatusPorId(int agendamentoId, String status) {
		try (var select = connection.prepareStatement("SELECT prestador_id, data_hora FROM agendamento WHERE id = ?")) {
			select.setInt(1, agendamentoId);
			try (ResultSet rs = select.executeQuery()) {
				if (rs.next()) {
					int prestadorId = rs.getInt("prestador_id");
					String dataHora = rs.getString("data_hora");

					inserirHistorico(agendamentoId, prestadorId, dataHora, status);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void inserirHistorico(int agendamentoId, int prestadorId, String dataHora, String status) {
		try (var histStmt = connection.prepareStatement(INSERIR_HISTORICO)) {
			histStmt.setInt(1, agendamentoId);
			histStmt.setInt(2, prestadorId);
			histStmt.setString(3, dataHora);
			histStmt.setString(4, status);
			histStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
