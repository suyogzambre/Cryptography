package com.crypto.project.service;
import java.util.Optional;import org.glassfish.jersey.jaxb.internal.XmlCollectionJaxbProvider.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crypto.project.model.ApplicationUser;


@Repository

public interface ApplicationUserRepository  extends JpaRepository<ApplicationUser, Long>{
	Optional<ApplicationUser> findByUsername(String username);
//	Optional<ApplicationUser> findByemailId(String email);

	
}
