package net.guilhermejr.sistema.supermercadoservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itens")
@Audited
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JsonBackReference
    private Compra compra;

    @ManyToOne
    private Produto produto;

    @Column(nullable = false)
    private BigDecimal qtd;

    @Column(nullable = false)
    private BigDecimal valor;

}
