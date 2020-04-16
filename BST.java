
public class BST<T extends Comparable<T>>{
	
	private class Node<T>{ //class Node creating object T with different values such as value, left, and right
		Node<T> left,right;
		T value;
		public Node(T item){
			value=item;
			left=null;
			right=null;
		}
	}
	
	Node<T> root;//Create node root
	
	public boolean find(T element){ //find function, return the value obtained by function find(node, element)
		return find(root,element);
	}
	
	protected boolean find(Node<T> node,T element){ //Protected function find
		if(node==null) //If there is no nodes, return false
			return false;
		if(element.compareTo(node.value)==0) //If the element we're looking for is the same as the value of the node
			return true; //Return true
		else if(element.compareTo(node.value)<0) //If the element is less than the value of the node, take the left
			return find(node.left,element); // branch of the tree
		else
			return find(node.right,element); //Else if the element is more than the value of the node, take the right
	} //branch of the tree

	public void insert(T element){ //insert function to insert an element to the tree
		root=insert(root,element); //The root of the tree is the value returned by the function insert(root, element)
	}

	public Node<T> insert(Node<T> node,T element){ //Insert function
		if(node==null) //If the tree is empty
			return new Node<T>(element); //Create node with value element and return it
		if(element.compareTo(node.value)<0) //If the value of the element is less than the value of the actual node being
			node.left=insert(node.left,element); //Compared, call the function again using the left branch of the tree
		else
			node.right=insert(node.right,element); //Else if the value of the element is more than the value of the actual
		return node; //Node being compare, call the function again using the right branch of the tree and return node.
	}

	public void delete(T element){ //public function delete element
		root=delete(root,element); //root takes the value returned by function delete(root,element)
	}

	public Node<T> delete(Node<T> node, T element){ //delete function
		if(node==null) //If the tree is empty, return null
			return null;
		if(element.compareTo(node.value)>0){ //If the value of the element is more than the actual node, take the right 
			node.right=delete(node.right,element); //branch of the tree and return the node
			return node;
		}
		else if(element.compareTo(node.value)<0){ //If the value of the element is less than the actual node, take the left
			node.left=delete(node.left,element);//branck of the tree and return the node
			return node;
		}
		else{	//If the element has the value requested 
			if(node.left==null)
				return node.right; //If the left branch doesn't exist, the right branch 'goes up'
			else if(node.right==null)
				return node.left;//If the right branch doesn't exist, the left branch 'goes up'
			else{
				if(node.right.left==null){ //else if they're both null
					node.value=node.right.value; //node takes value below it
					node.right=node.right.right;
				}
				else
					node.value=removeSmallest(node.right); //Else the removed is the smallest value
			}
			return node;
		}
	}

	public T removeSmallest(Node<T> node){ //removeSmallest function to remove the smallest value of a tree
		if(node.left.left==null){ //If the value of the node two steps down the branch tree is null
			T small=node.left.value; //small object takes the value of the left branch node
			node.left=node.left.right; //The node on the left branch now takes the value of the right branch node two steps
			return small; //down
		}
		else
			return removeSmallest(node.left); //else recursively call the function on the next left node.
	}

	public void print(){ //public function print
		inOrderTraversal(root); //calls inOrderTraversal function to print the tree in traversal order
	}
	
	public void inOrderTraversal(Node<T> node){ //inOrderTraversal function to print the whole tree 
		if(node!=null){ //If the node is not empty
			inOrderTraversal(node.left); //call function to print the value of the left node recursively
			System.out.print(node.value);
			inOrderTraversal(node.right);//call function to print the value of the right node recursively
		}
	}
	
}