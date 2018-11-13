package fr.m2ptsi.banque_persistence.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe generale d'exception du projet
 * 
 * @author Ahmed CONDE
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class BanqueException extends Exception {

	private String message;
}
