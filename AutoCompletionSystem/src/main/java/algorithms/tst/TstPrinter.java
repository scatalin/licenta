package algorithms.tst;

/**
 * Created by Catalin on 2/21/2015.
 */
public class TstPrinter {

    private StringBuilder stringBuilder;

    TstPrinter(){
    }

    public String prettyPrint(TstNode root){
        stringBuilder = new StringBuilder();
        recursivePrettyPrint(root, 0, "");
        return stringBuilder.toString();
    }

    private void recursivePrettyPrint(TstNode node, int level, String header) {
        if(node == null){
            return;
        }
        recursivePrettyPrint(node.getLeftChild(), level + 1, "l ");
        stringBuilder.append(indentation(level)).append(header).append(node.getCharacter()).append("\n");
        recursivePrettyPrint(node.getMiddleChild(), level + 1, "m ");
        recursivePrettyPrint(node.getRightChild(), level + 1, "r ");
    }

    private String indentation(int level){
        String indent = "";
        String space = "   ";
        for(int i = 0; i <= level; i++){
            indent += space;
        }
        return indent;
    }
}
