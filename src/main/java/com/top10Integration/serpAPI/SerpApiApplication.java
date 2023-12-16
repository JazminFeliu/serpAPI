package com.top10Integration.serpAPI;
import com.top10Integration.serpAPI.Models.Repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SerpApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SerpApiApplication.class, args);
	}

	@Autowired
	private IAuthorRepository repository;

		@Override
				public void run(String... arg) throws Exception{


			/*
					Author author1 = new Author("EicYvbwAAAAJ","Ronald C Kessler", "Verified email at hcp.med.harvard.edu", "Australia",
					"https://scholar.google.com/citations?user=EicYvbwAAAAJ&hl=en", "20");
					repository.save(author1);

					Author author2 = new Author("kq0NYnMAAAAJ","Mike Robb", "Verified email at imperial.ac.uk","Mexico",
					"http://www.imperial.ac.uk/people/mike.robb", "20");
					repository.save(author2);
			*/
		}

}

