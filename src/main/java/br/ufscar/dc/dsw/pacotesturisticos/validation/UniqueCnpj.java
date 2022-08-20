package br.ufscar.dc.dsw.pacotesturisticos.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueCnpjValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCnpj {
    String message() default "{UniqueCnpj.agencia.email}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
