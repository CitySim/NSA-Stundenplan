package server.operations;

import java.util.Random;

/**
 * Class used encrypt and generate passwords.
 * 
 * @author dennis.markmann
 * @since JDK.1.7.0_25
 * @version 1.0
 */

public class PasswordEncryptor {

	public String generateEncryptedPassword() {

		return this.encryptPassword(this.generatePassword());

	}

	private String encryptPassword(final String password) {

		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		BCrypt.gensalt();
		hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

		return hashed;
	}

	private String generatePassword() {

		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		final String someDigits = "0123";
		final String someSpecial = "!%&+-@";

		return generate(10, someDigits, someSpecial, alphabet);
	}

	private static Random random = new Random();

	public static String generate(final String alphabet, final int length) {
		final String digits = "0123456789";
		final String special = "!\"#$%&'()*+,-./:;<=>?@";
		try {
			return generate(length, digits, special, alphabet);
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}
		return special;
	}

	/**
	 * Creates a String of the given length, containing random characters from
	 * the given alphabets, but at least one character from each alphabet. If
	 * there are more than 'length' alphabets, one random character is chosen
	 * from each of the 'length' first alphabets. <br />
	 * <br />
	 * At least one alphabet must be given. If an empty String or
	 * <code>null</code> is encountered as an alphabet, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param length
	 *            The length of the string to generate
	 * @param alphabets
	 *            The alphabets to use
	 * @return The generated string
	 * @throws IllegalArgumentException
	 *             if not alphabets are given, or an empty of <code>null</code>
	 *             alphabet is encountered
	 */
	public static String generate(final int length, final String... alphabets) {
		if (alphabets.length == 0) {
			throw new IllegalArgumentException(
					"At least one alphabet must be given");
		}
		final StringBuffer result = new StringBuffer();
		final StringBuffer all = new StringBuffer();
		for (final String alphabet : alphabets) {
			if (alphabet == null || alphabet.equals("")) {
				throw new IllegalArgumentException("Invalid alphabet: "
						+ alphabet);
			}
			final StringBuffer sb = new StringBuffer(alphabet);
			result.append(selectRandom(sb, 1));
			if (result.length() == length) {
				return shuffle(result).toString();
			}
			all.append(sb);
		}
		result.append(selectRandom(all, length - result.length()));
		return shuffle(result).toString();
	}

	/**
	 * Returns a StringBuffer containing 'n' characters chosen randomly from the
	 * given alphabet.
	 * 
	 * @param alphabet
	 *            The alphabet to choose from
	 * @param n
	 *            The number of characters to choose
	 * @return A StringBufer with 'n' characters chosen randomly from the given
	 *         alphabet
	 */
	private static StringBuffer selectRandom(final StringBuffer alphabet,
			final int n) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		return sb;
	}

	/**
	 * Shuffles the given StringBuffer in place and returns it
	 * 
	 * @param sb
	 *            The StringBuffer to shuffle and return
	 * @return The given StringBuffer, shuffled randomly
	 */
	private static StringBuffer shuffle(final StringBuffer sb) {
		for (int i = 0; i < sb.length(); i++) {
			final int i0 = random.nextInt(sb.length());
			final int i1 = random.nextInt(sb.length());
			final char c = sb.charAt(i0);
			sb.setCharAt(i0, sb.charAt(i1));
			sb.setCharAt(i1, c);
		}
		return sb;
	}
}