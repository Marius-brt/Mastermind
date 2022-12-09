package Graphical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.*;

public class RegexFilter extends DocumentFilter {
    private Pattern pattern;

    public RegexFilter(String pat) {
        pattern = Pattern.compile(pat);
    }

    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        Matcher m = pattern.matcher(newStr);
        if (m.matches()) {
            super.insertString(fb, offset, string, attr);
        } else {
        }
    }

    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
            throws BadLocationException {

        if (length > 0)
            fb.remove(offset, length);
        insertString(fb, offset, string, attr);
    }
}
