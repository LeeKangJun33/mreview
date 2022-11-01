package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews(){
        IntStream.rangeClosed(1,200).forEach(i->{

            Long mno =(long)(Math.random()*100)+1;

            Long mid = ((long)(Math.random()*100+1));

            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5)+1 )
                    .text("이영화에대한 느낀점은"+i)
                    .build();

            reviewRepository.save(movieReview);
        });
    }
    @Test
    public void testGetMovieReviews(){

        Movie movie = Movie.builder().mno(92L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview ->{
            System.out.println(movieReview.getReviewnum());
            System.out.println("\t"+movieReview.getGrade());
            System.out.println("\t"+movieReview.getText());
            System.out.println("\t"+movieReview.getMember().getEmail());
            System.out.println("==============================");
        });
    }
}
