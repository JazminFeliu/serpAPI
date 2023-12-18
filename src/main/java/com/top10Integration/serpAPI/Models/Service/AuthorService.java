package com.top10Integration.serpAPI.Models.Service;

import com.top10Integration.serpAPI.Models.Entity.Author;
import com.top10Integration.serpAPI.Models.Repository.IAuthorRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {

    @Autowired
    private IAuthorRepository repository;

    public List<Author> listAll() {
        return repository.findAll();
    };

    public Author findAuthorByID(Integer id) {
        return repository.findById(id).get();
    };

    public Author updateAuthor(Author author) {
        return repository.save(author);
    };

    public void deleteAuthor(Integer id) {
        repository.deleteById(id);
    };


    private String searchIdAuthorByName(String name) {
        //busca en SerpAPI engin profiles el nombre del author y devuelve el id_author. TODO: retirar APIKEY personal
        //parametrizar en application.properties

        Map<String, String> parameter = new HashMap<>();

        parameter.put("api_key", "tu API_KEY aqui");
        parameter.put("engine", "google_scholar_profiles");
        parameter.put("hl", "en");
        parameter.put("mauthors", name);
        String idAuthor = "";
        try {
            var dir = "https://serpapi.com/search.json?engine="
                    + parameter.get("engine") + "&mauthors="
                    + parameter.get("mauthors") + "&hl="
                    + parameter.get("hl") + "&api_key="
                    + parameter.get("api_key");
            URL url = new URL(dir);

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.connect();
            int resCod = con.getResponseCode();
            System.out.println(resCod);
            if (resCod != 200) {
                throw new RuntimeException("Ocurrió un error: " + resCod);
            } else {

                InputStream is = url.openStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String jsonText = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonText += line + "\n";
                }
                is.close();
                bufferedReader.close();
                System.out.println(jsonText);

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(jsonText);
                JSONObject mainJSonObject = (JSONObject) obj;

                JSONArray jsonArrayProfile = (JSONArray) mainJSonObject.get("profiles");

                JSONObject jsonObjectprofile = (JSONObject) jsonArrayProfile.get(0);
                idAuthor = (String) jsonObjectprofile.get("id_author");

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(idAuthor);
        }
        return idAuthor;
    }


    private Author searchAuthorByidAuthor(String idAuthor) {

        Author author = new Author();
        author.setAuthor_ID(idAuthor);
        Map<String, String> parameter = new HashMap<>();

        parameter.put("api_key", "tu API_KEY aqui");
        parameter.put("engine", "google_scholar_author");
        parameter.put("hl", "en");
        parameter.put("author_id", idAuthor);
        try {

            var dir = "https://serpapi.com/search.json?engine="
                    + parameter.get("engine") + "&author_id="
                    + parameter.get("author_id") + "&hl="
                    + parameter.get("hl") + "&api_key="
                    + parameter.get("api_key");
            URL url = new URL(dir);

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.connect();

            int resCod = con.getResponseCode();
            if (resCod != 200) {
                throw new RuntimeException("Ocurrió un error: " + resCod);
            } else {
                InputStream is = url.openStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String jsonText = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonText += line + "\n";
                }
                is.close();
                bufferedReader.close();
                System.out.println(jsonText);

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(jsonText);
                JSONObject mainJSonObject = (JSONObject) obj;

                JSONObject jsonObjectMetadata = (JSONObject) mainJSonObject.get("search_metadata");

                String author_url = (String) jsonObjectMetadata.get("google_scholar_author_url");
                author.setAuthor_url(author_url);
                System.out.println("Author URL : " + author_url);


                JSONObject jsonObjectAuthor = (JSONObject) mainJSonObject.get("author");
                String name = (String) jsonObjectAuthor.get("name");
                author.setName(name);
                System.out.println("Name : " + name);

                String affiliations = (String) jsonObjectAuthor.get("affiliations");
                author.setAffiliations(affiliations);
                System.out.println("Affiliations : " + affiliations);

                String email = (String) jsonObjectAuthor.get("email");
                author.setEmail(email);
                System.out.println("Email : " + email);

                JSONObject jsonObjectParameters = (JSONObject) mainJSonObject.get("search_parameters");

                String author_id = (String) jsonObjectParameters.get("author_id");
                System.out.println("Author ID: " + author_id);

                JSONArray jsonArrayArticles = (JSONArray) mainJSonObject.get("articles");
                System.out.println("Articles:");

                Integer tarticles = jsonArrayArticles.size();
                author.setTarticles(tarticles);
                System.out.println("Total Articles: " + tarticles);

                for (int i = 0; i < jsonArrayArticles.size(); i++) {
                    JSONObject jsonArticle = (JSONObject) jsonArrayArticles.get(i);
                    System.out.println("      Article No: " + (i + 1));

                    String title = (String) jsonArticle.get("title");
                    System.out.println("      Title: " + title);

                    String link = (String) jsonArticle.get("link");
                    System.out.println("      Link: " + link);

                    String year = (String) jsonArticle.get("year");
                    System.out.println("      Year: " + year);

                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;

    }

    public Author searchAuthor(String name, String idAuthor) {
        searchIdAuthorByName(name);
        Author author = searchAuthorByidAuthor(idAuthor);
        return author;
    }

    public Author saveAuthor(String name, String idAuthor) {
        Author author = searchAuthor(name, idAuthor);
        return repository.save(author);
    };
}



