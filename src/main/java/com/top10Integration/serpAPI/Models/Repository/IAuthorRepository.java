package com.top10Integration.serpAPI.Models.Repository;

import com.top10Integration.serpAPI.Models.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer>  {
}
