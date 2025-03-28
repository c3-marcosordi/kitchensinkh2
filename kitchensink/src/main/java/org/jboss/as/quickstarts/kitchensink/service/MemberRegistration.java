package org.jboss.as.quickstarts.kitchensink.service;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import java.util.logging.Logger;

@Service
public class MemberRegistration {

    @Autowired
    private Logger log;

    @Autowired
    private EntityManager em;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        em.persist(member);
        eventPublisher.publishEvent(member);
    }
}