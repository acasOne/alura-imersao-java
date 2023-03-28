
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;



public class App {
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_NEGRIGO = "\u001b[1m";
    public static final String ANSI_LETRAAZUL = "\u001b[34m";
    public static void main(String[] args) throws Exception {
        
        //fazer uma conexao http //buscar os top 250 - os dados do imdb
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String imdbkey = System.getenv("IMDB_API_KEY");

        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        //pegar apenas os dados de interesse (titulo, poster, classificaçao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados da forma que quiser
        System.out.println("Variavel" + imdbkey);

        for (Map<String,String>  filme : listaDeFilmes) {
            System.out.println(ANSI_NEGRIGO + "Poster:" + ANSI_RESET + " " + ANSI_BLUE_BACKGROUND + filme.get("title") + ANSI_RESET);
            System.out.println(ANSI_NEGRIGO + "URL da imagem: "  + ANSI_RESET + ANSI_LETRAAZUL + filme.get("image") + ANSI_RESET);

            double numeroEstrelas = Double.parseDouble(filme.get("imDbRating"));
            System.out.println(ANSI_RED_BACKGROUND + "Classificação: " + numeroEstrelas + " " +ANSI_RESET );

            for(double i=0; i <= numeroEstrelas; i++) {
                System.out.print("\u2B50");
            }
            System.out.println();
            System.out.println();
        }
    }
}
