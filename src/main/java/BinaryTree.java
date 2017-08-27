public class BinaryTree {

    public static void main(String[] args) {
        TreeElement randomBinaryTree = generateRandomBinaryTree(6, 0.5, 10);
        System.out.println(randomBinaryTree.toString());

        System.out.println(findInTree(5, randomBinaryTree));
        System.out.println(getTreeHeight(randomBinaryTree));
    }

    public static TreeElement generateBalancedTree(int elementsNumber, int range)
    {
        TreeElement treeElement = new TreeElement();

        int leftTreeSize = 0;
        int rightTreeSize = 0;
        if (elementsNumber > 0) {
            leftTreeSize = elementsNumber/2;
            rightTreeSize = elementsNumber - leftTreeSize;

            int randomValue = (int) (Math.random()*range);

            treeElement.setElementValue(randomValue);
            treeElement.setLeftElement(generateBalancedTree(leftTreeSize, range));
            treeElement.setRightElement(generateBalancedTree(rightTreeSize, range));

        }
        return treeElement;
    }

    public static int getTreeHeight(TreeElement root)
    {
        if (root == null) {
            return 0;
        } else {
            int leftTreeHeight = getTreeHeight(root.getLeftElement());
            int rightTreeHeight = getTreeHeight(root.getRightElement());
            if (leftTreeHeight > rightTreeHeight) {
                return leftTreeHeight+1;
            } else {
                return rightTreeHeight+1;
            }
        }

    }


    public static boolean findInTree(int searchedValue, TreeElement root)
    {
        boolean founded = false;
        if (root != null) {
            if(searchedValue == root.getElementValue()) {
                founded = true;
            } else {
                founded = findInTree(searchedValue, root.getLeftElement());
                if(!founded) {
                    founded = findInTree(searchedValue, root.getRightElement());
                }
            }
        }

        return founded;
    }

    public static void preorder(TreeElement root)
    {
        if (root != null) {
            System.out.println(root.getElementValue());
            preorder(root.getLeftElement());
            preorder(root.getRightElement());
        }
    }

    public static void inorder(TreeElement root)
    {
        if (root != null) {
            inorder(root.getLeftElement());
            System.out.println(root.getElementValue());
            inorder(root.getRightElement());
        }
    }

    public static void postorder(TreeElement root)
    {
        if (root != null) {
            postorder(root.getLeftElement());
            postorder(root.getRightElement());
            System.out.println(root.getElementValue());
        }
    }

    public static TreeElement generateRandomBinaryTree(int elementsNumber, double probability, int range) {

        /* Tworzymy korzeń */
        TreeElement rootElement = new TreeElement((int)(Math.random() * range));

        /* Tymczasowy element, którym przesuwamy się po drzewie,
        zaczynając od korzenia */
        TreeElement jumpFrog = rootElement;

        /* For wykonujemy tyle razy ile elementów mamy dodać,
        -1 ponieważ utworzyliśmy korzeń */
        for (int i = 0; i < elementsNumber -1; i++ ){

            /* Tworzymy nowy element, który dodamy do drzewa */
            TreeElement newElement = new TreeElement((int)(Math.random() * range));

            /* zmienna przechowująca informację czy został element już dodany */
            boolean newElementWasAdded = false;

            do {
                /* idziemy w lewe poddrzewo */
                if(Math.random() < probability) {
                    if (jumpFrog.getLeftElement() != null) {
                        /* skaczemy na element który wskazuje lewy wskaźnik */
                        jumpFrog = jumpFrog.getLeftElement();
                    } else {
                        /* ustawiamy lewy wskaźnik aktualnemu elementowy na nowo utworzony */
                        jumpFrog.setLeftElement(newElement);
                        newElementWasAdded = true;
                    }

                /* idziemy w prawe poddrzewo */
                } else {
                    if (jumpFrog.getRightElement() != null) {
                        /* skaczemy na element który wskazuje prawy wskaźnik */
                        jumpFrog = jumpFrog.getRightElement();
                    } else {
                        /* ustawiamy prawy wskaźnik aktualnemu elementowy na nowo utworzony */
                        jumpFrog.setRightElement(newElement);
                        newElementWasAdded = true;
                    }
                }
            } while (!newElementWasAdded);
            System.out.println(newElement.toString());
        }

        return rootElement;
    }
}
