public class TextEditor {
    private Stack<String> undoStack = new Stack<>();
    private StringBuilder document = new StringBuilder();

    public void addText(String text){
        document.append(text);
        undoStack.push("Added '" + text +
         "'");
    }

    public void deleteText(int lenght){
        if (lenght > document.length()){
            lenght = document.length();
        }
        String deletedText = document.substring(document.length() - lenght);
        document.delete(document.length() - lenght, document.length());
        undoStack.push("Deleted '" + deletedText + "'");
    }

    public void undo(){
        if(undoStack.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }

        String lastAction = undoStack.pop();
    }
    public void printDocument(){
        System.out.println("Document: " + document.toString());
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.addText("Hello,");
        editor.addText("World");
        editor.printDocument();

        editor.undo();
        editor.printDocument();

        editor.undo();
        editor.printDocument();
    }
}
