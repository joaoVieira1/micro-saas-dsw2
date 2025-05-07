package com.microsaas.tattoo.model.utils;

public enum StatusAgenda {
	 OCUPADO,
	 DESOCUPADO;

	    // Método utilitário para converter de String (ex: do banco de dados) para enum
	    public static StatusAgenda fromString(String status) {
	        if (status == null) return null;
	        return StatusAgenda.valueOf(status.toUpperCase());
	    }

	   

}
