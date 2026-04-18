package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.dto.ContactRequest;

public interface ContactRepository extends JpaRepository<ContactRequest, Long> {

}
