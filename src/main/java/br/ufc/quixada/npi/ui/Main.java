package br.ufc.quixada.npi.ui;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.ufc.quixada.npi.model.Contato;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			try {
				tx.begin();
				em.persist(new Contato("João", "(88)3452-4567"));
				em.persist(new Contato("Maria", "(88)3452-4568"));
				em.persist(new Contato("José", "(88)3452-4569"));
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				System.out.println("Não foi possível realizar a inserção");
			}

		    List<Contato> l = em.createQuery("from Contato", Contato.class).getResultList();

		    for (Contato c : l) {
		    	System.out.println(c);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}
