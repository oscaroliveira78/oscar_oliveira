package br.com.infnet.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infnet.models.Localizacao;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
}