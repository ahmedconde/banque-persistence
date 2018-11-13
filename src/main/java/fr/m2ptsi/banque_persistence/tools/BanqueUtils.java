package fr.m2ptsi.banque_persistence.tools;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.*;
import java.util.Date;

import com.google.common.hash.Hashing;

public class BanqueUtils {
	
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	/**
	 * @param word
	 * @return
	 */
	public static String capitalize(String word) {
		if(!isNumtelOK(word)) {
			return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
		} else {
			return null;
		}
	}

	/**
	 * LocalDate === Date
	 *
	 * @param localDate
	 * @return
	 */
	public static Date asDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	/**
	 * @param localDateTime
	 * @return
	 */
	public static Timestamp asDate(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return java.sql.Timestamp.valueOf(localDateTime);
	}

	/**
	 * String === LocalDate
	 *
	 * @param dateString
	 * @return
	 */
	public static LocalDate parse(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	/**
	 * LocalDate === String
	 *
	 * @param date
	 * @return
	 */
	public static String format(LocalDate date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMATTER.format(date);
	}

	/**
	 * Date === LocalDate
	 *
	 * @param date
	 * @return
	 */
	public static LocalDate asLocalDate(Date date) {
		if (date == null) {
			return null;
		}
		Instant instant = date.toInstant();
		return instant.atZone(ZoneId.systemDefault()).toLocalDate();
	}


	/**
	 * @param dateString
	 * @return
	 */
	public static boolean validDate(String dateString) {
		return parse(dateString) != null;
	}

	/**
	 * Calcul l'age à partir de la date passée en parametre
	 *
	 * @param ddn
	 * @return
	 */
	public static int calculAge(LocalDate ddn) {
		LocalDate maintenant = LocalDate.now();
		Period period = Period.between(ddn, maintenant);
		return period.getYears();
	}

	/**
	 * Permet de hasher un string
	 *
	 * @param mdp
	 * @return
	 */
	public static String hashGuava(String mdp) {
		return Hashing.sha512().hashString(mdp, StandardCharsets.UTF_8).toString();
	}

	/**
	 * @param mot
	 * @return
	 */
	public static boolean isNullOrSpace(String mot) {
		return mot == null || mot.length() == 0;
	}

	/**
	 * Vérifie le format d'une adresse mail
	 * passée en paramètre String
	 * @param mailAd
	 * @return true si le format est correct
	 */
	public static boolean isMailOK(String mailAd){
		mailAd = mailAd.trim();
		String regexMail ="[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,6}";
		return (mailAd.matches(regexMail));
	}

	/**
	 * Vérifie le format d'un numero de téléphone français
	 * passé en paramètre String
	 * @param numTel
	 * @return true si le format est correct
	 */
	public static boolean isNumtelOK(String numTel){
		numTel = numTel.trim();
		String regexTel ="0[1-9][0-9]{8}";
		numTel = numTel.replaceAll(" ", "");
		numTel = numTel.replaceAll("-", "");
		numTel = numTel.replaceAll("\\.", "");
		return (numTel.matches(regexTel));
	}


	/**
	 * Vérifie le format d'un numero de siret
	 * passé en paramètre String
	 * @param numSiret
	 * @return true si le format est correct
	 */
	public static boolean isSiretOK(String numSiret){
		numSiret = numSiret.trim();
		numSiret = numSiret.replaceAll(" ", "");
		numSiret = numSiret.replaceAll("-", "");
		numSiret = numSiret.replaceAll("\\.", "");
		//Verif des digits (pas de lettres)
		String regexSiret = "[0-9]{"+numSiret.length()+"}";
		if (numSiret.matches(regexSiret)) {
			//Extrait le num Siren (9 chiffres)
			//String numSiren = numSiret.substring(0, 9);
			StringBuilder numSiretInv = new StringBuilder(numSiret).reverse();
			numSiret = numSiretInv.toString();
			int sum = 0;
			//Algo de verif (formule de Luhn)
			for (int i = 0; i < numSiret.length(); i++) {
				int v = Integer.valueOf(numSiret.substring(i, i+1));
				if (i % 2 != 0) {
					v *= 2;
					if (v > 9) {
						int s = v % 10;
						s += (v / 10);
						v = s;
					}
				}
				sum += v;
			}
			//Le siren est ok si la somme est un multiple de 10
			return (0 == (sum % 10));
		} else {
			return false;
		}
	}

	/**
	 * Vérifie le format d'un nom
	 * passé en paramètre String
	 * @param nom
	 * @return true si le format est correct
	 */
	public static boolean isNomOk(String nom){
		nom= nom.trim();
		String regexNom= "[a-zA-Z']*";
		return nom.matches(regexNom);

	}


}
