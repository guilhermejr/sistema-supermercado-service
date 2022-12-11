package net.guilhermejr.sistema.supermercadoservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compras")
@Audited
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    private Supermercado supermercado;

    @OneToMany(mappedBy = "compra")
    @JsonManagedReference
    private List<Item> items;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime data;

    @Column(nullable = false)
    private String chaveDeAcesso;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private UUID usuario;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime criado;

}
