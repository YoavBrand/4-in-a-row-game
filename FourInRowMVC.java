package Q1;
import javax.swing.*;

public class FourInRowMVC {

	public static void main(String[] args) {

		FourInRowModel model = new FourInRowModel();
		FourInRowView view = new FourInRowView(model);
		FourInRowController controller = new FourInRowController(model, view);
		
	}

}

