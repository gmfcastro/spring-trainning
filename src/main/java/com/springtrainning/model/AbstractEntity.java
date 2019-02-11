package com.springtrainning.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * @MappedSuperclass - Indica que a classe será mapeada nas entidades que herdarem dela, assim ela não possui uma tabela no banco .
 * implements Serializable - utiliza-se quando é pretendido armazenar uma copia do objeto, e envia-lo para outro processo ou através da rede.
 * @Id - Especifica o atributo como primary key.
 * @GeneratedValue - Deve ser utilizado junto a uma @id annotation, e especifica a estratégia de geração de primary key.
 * @Override equals & hashCode -
 * */


@MappedSuperclass
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }
    AbstractEntity that = (AbstractEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
