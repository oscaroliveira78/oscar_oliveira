package br.com.infnet.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infnet.models.Ingresso;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
}