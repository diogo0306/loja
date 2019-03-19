package br.com.eclinic.util;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.eclinic.criptografia.ConvertPasswordToMD5;

public class Util {

	/**
	 * Método retorna o valor formatado.
	 * 
	 * @param valor
	 * @return
	 */
	public static String obterValorFormatado(BigDecimal valor) {
		String retorno = "";
		if (valor != null) {
			retorno = new DecimalFormat("#,###,##0.00").format(valor);
		}
		return retorno;
	}

	/**
	 * Método para comparar as das e retornar o numero de dias de diferença entre
	 * elas
	 *
	 * Compare two date and return the difference between them in days.
	 *
	 * @param dataLow  The lowest date
	 * @param dataHigh The highest date
	 *
	 * @return int
	 */
	public static int dataDiff(java.util.Date dataLow, java.util.Date dataHigh) {

		GregorianCalendar startTime = new GregorianCalendar();
		GregorianCalendar endTime = new GregorianCalendar();

		GregorianCalendar curTime = new GregorianCalendar();
		GregorianCalendar baseTime = new GregorianCalendar();

		startTime.setTime(dataLow);
		endTime.setTime(dataHigh);

		int dif_multiplier = 1;

		// Verifica a ordem de inicio das datas
		if (dataLow.compareTo(dataHigh) < 0) {
			baseTime.setTime(dataHigh);
			curTime.setTime(dataLow);
			dif_multiplier = 1;
		} else {
			baseTime.setTime(dataLow);
			curTime.setTime(dataHigh);
			dif_multiplier = -1;
		}

		int result_years = 0;
		int result_months = 0;
		int result_days = 0;

		// Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import
		// acumulando
		// no total de dias. Ja leva em consideracao ano bissesto
		while (curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR)
				|| curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)) {

			int max_day = curTime.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			result_months += max_day;
			curTime.add(GregorianCalendar.MONTH, 1);

		}

		// Marca que é um saldo negativo ou positivo
		result_months = result_months * dif_multiplier;

		// Retirna a diferenca de dias do total dos meses
		result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));

		return result_years + result_months + result_days;
	}

	public static String criptografia(String senha) {
		try {
			return ConvertPasswordToMD5.convertPasswordToMD5(senha);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int mesesEntre(Date a, Date b) {
		Calendar inicial = Calendar.getInstance();
		inicial.setTime(a);

		Calendar fim = Calendar.getInstance();
		fim.setTime(b);

		int count = 0;

		while (inicial.get(Calendar.MONTH) != fim.get(Calendar.MONTH)) {
			inicial.add(Calendar.MONTH, 1);
			count++;
		}

		return count;
	}

	public static String mesFormatado(int mes) {
		if (mes == 1) {
			return "Janeiro";
		}
		if (mes == 2) {
			return "Fevereiro";
		}
		if (mes == 3) {
			return "Março";
		}
		if (mes == 4) {
			return "Abril";
		}
		if (mes == 5) {
			return "Maio";
		}
		if (mes == 6) {
			return "Junho";
		}
		if (mes == 7) {
			return "Julho";
		}
		if (mes == 8) {
			return "Agosto";
		}
		if (mes == 9) {
			return "Setembro";
		}
		if (mes == 10) {
			return "Outubro";
		}
		if (mes == 11) {
			return "Novembro";
		}
		if (mes == 12) {
			return "Dezembro";
		} else {
			return "";
		}

	}

	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static void main(String[] args) {
		// 202cb962ac59075b964b07152d234b70
		System.out.println(criptografia("123"));
	}
}
