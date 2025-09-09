package br.com.carlosbrito.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * @author carlos.brito
 * Criado em: 09/09/2025
 */
public class FormatadorBigDecimal {

    private static final DecimalFormat decimalFormat;

    static{
        //Aqui criamos os simbolos para formatação do BigDecimal salário, informando linguagem e país
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt","BR"));
        //Para milhar, colocamos que o separador de grupo é o .
        simbolos.setGroupingSeparator('.');
        //Para decimal, definimos a virgula
        simbolos.setDecimalSeparator(',');

        decimalFormat =  new DecimalFormat("#,##0.00",simbolos);
    }

    //Aqui crio uma função static para ser usada em qualquer lugar do código sem instanciar um objeto
    public static String formatar(BigDecimal valor){
        return decimalFormat.format(valor);
    }

}
