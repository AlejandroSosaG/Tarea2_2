package tarea2_2;

import java.io.File;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Tarea2_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Indicaremos la ruta del fichero xml
			//Src es el nombre raiz de nuestro proyecto, main es la primera carpeta, resources la siguiente, dentro de xml encontraremos el fichero
			//Esta es la ruta relativa.
			File xml = new File("C:\\Users\\asosa\\Documents\\Tarea2_2\\src\\tarea2_2\\compras.xml");
			//Creamos los objetos que nos permitiran leer el fichero
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			//Le pasamos el XML
			Document doc = (Document) db.parse(xml);
			((Node) doc.getDefaultRootElement()).normalize();
			System.out.println("Elemento raiz:" + doc.getDefaultRootElement().getName());
			NodeList nodeList = ((org.w3c.dom.Document) doc).getElementsByTagName("compra");
			//Creamos un bucle para leer los datos del xml y los mostramos en la consola
			double productTotal = 0, descuentoTotal = 0, precioTotal = 0;
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					NodeList nodeListProducto = ((org.w3c.dom.Document) doc).getElementsByTagName("producto");
					System.out.println("Fecha: "+ ( (org.w3c.dom.Document) eElement).getElementsByTagName("fecha").item(0).getTextContent());
					double cantidad = 0, descuento = 0, total;
					int cont = 0;
					for (int j = 0; j < nodeListProducto.getLength(); j++) {
						if(((org.w3c.dom.Document) eElement).getElementsByTagName("unidades").item(j) != null) {
							cantidad= Double.parseDouble(((org.w3c.dom.Document) eElement).getElementsByTagName("unidades").item(j).getTextContent().replace(",",".")); 
						}else {
							cantidad = 1;
						}
						cont+=cantidad;
						if(((org.w3c.dom.Document) eElement).getElementsByTagName("descuento").item(j) != null) {
							descuento=Double.parseDouble(((org.w3c.dom.Document) eElement).getElementsByTagName("descuento").item(j).getTextContent().replace(",","."));
						}
						
					}
					productTotal+=cont;
					descuentoTotal+= descuento;
					total=(Double.parseDouble(((Node) ((org.w3c.dom.Document) eElement).getElementsByTagName("precio_unidad")).getTextContent().replace(",","."))-descuento);
					precioTotal+= total;
					System.out.println("Cantidad: " + cont);
					System.out.println("Descuento: " + descuento);
					System.out.println("Precio final: " + total);
					
					}
				}
			System.out.println("Cantidades total: " + productTotal);
			System.out.println("Descuento total: " + descuentoTotal);
			System.out.println("Precio total: " + precioTotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
