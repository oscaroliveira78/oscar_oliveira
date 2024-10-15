package br.com.infnet.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infnet.models.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}