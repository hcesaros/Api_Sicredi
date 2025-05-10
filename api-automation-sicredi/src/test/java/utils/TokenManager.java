package utils;

/**
 * Classe utilitária responsável por armazenar e fornecer o token de autenticação
 * obtido durante o processo de login.
 */
public class TokenManager {

    // Token de autenticação armazenado de forma estática
    private static String token;

    /**
     * Armazena o token de acesso obtido após o login.
     *
     * @param accessToken token JWT retornado pela API
     */
    public static void setToken(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalArgumentException("Token inválido: está nulo ou vazio.");
        }
        token = accessToken;
    }

    /**
     * Recupera o token armazenado para ser usado em outras requisições autenticadas.
     *
     * @return token JWT armazenado
     */
    public static String getToken() {
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("Token de autenticação não foi definido.");
        }
        return token;
    }

    /**
     * Limpa o token armazenado (caso necessário, por logout ou reset de sessão).
     */
    public static void clearToken() {
        token = null;
    }
}
