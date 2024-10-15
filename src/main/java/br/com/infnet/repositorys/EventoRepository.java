package br.com.infnet.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infnet.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}