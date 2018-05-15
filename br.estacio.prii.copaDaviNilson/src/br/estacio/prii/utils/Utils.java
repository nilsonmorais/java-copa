//    This is free and unencumbered software released into the public domain.
//
//    Anyone is free to copy, modify, publish, use, compile, sell, or
//    distribute this software, either in source code form or as a compiled
//    binary, for any purpose, commercial or non-commercial, and by any
//    means.
//
//    In jurisdictions that recognize copyright laws, the author or authors
//    of this software dedicate any and all copyright interest in the
//    software to the public domain. We make this dedication for the benefit
//    of the public at large and to the detriment of our heirs and
//    successors. We intend this dedication to be an overt act of
//    relinquishment in perpetuity of all present and future rights to this
//    software under copyright law.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//    EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
//    MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
//    IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
//    OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
//    ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
//    OTHER DEALINGS IN THE SOFTWARE.
//
//    For more information, please refer to <http://unlicense.org> 

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Alguns métodos utilitários.
 */
public class Utils {

    public static Dimension getWindowDimensions() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (dimension.getWidth() / 2);
        int y = (int) (dimension.getHeight() / 2);
        return new Dimension(x, y);
    }

    public static void showDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    /**
     * 
     * @param frame  The object to centralize
     */
    public static void centerWindow(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * 
     * @param label Tooltip for the icon
     * @param icon  The icon filename, ex: Add24.gif
     * @return 
     */
    public static JButton createButtonWithIcon(String label, String icon) {
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setToolTipText(label);
        if (icon != null) {
            button.setIcon(getIconForButton(icon));
        }
        return button;
    }
    public static ImageIcon getIconForButton(String iconName) {
        String urlString = "/toolbarButtonGraphics/general/" + iconName;
        URL url = Utils.class.getClass().getResource(urlString);
        return new ImageIcon(url);
    }
    public static Insets pad10 = new Insets(10, 10, 10, 10);
    public static Insets pad20 = new Insets(20, 20, 20, 20);
}