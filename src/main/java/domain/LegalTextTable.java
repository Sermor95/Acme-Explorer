
package domain;

public class LegalTextTable {

	//This is a simple class to group the multiple-object results of query C 11.
	//Attributes

	private final LegalText	text;
	private final long		count;


	public LegalTextTable(final LegalText text, final long count) {
		super();
		this.text = text;
		this.count = count;
	}

	public LegalText getText() {
		return this.text;
	}

	public long getCount() {
		return this.count;
	}

}
