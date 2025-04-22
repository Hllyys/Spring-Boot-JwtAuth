package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);//jwt token ı parse ettikten sonra kullancam
}
