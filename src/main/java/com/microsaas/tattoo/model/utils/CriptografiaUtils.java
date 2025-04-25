package com.microsaas.tattoo.model.utils;

import org.mindrot.jbcrypt.BCrypt;

public class CriptografiaUtils {
	
	public static String criptografarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean verificarSenha(String senhaDigitada, String senhaCriptografada) {
        return BCrypt.checkpw(senhaDigitada, senhaCriptografada);
    }
    
}
