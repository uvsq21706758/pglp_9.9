package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * classe DrawingTUI
 *
 */
public class DrawingTUI {
	
	/**
	 * interpretation de la commande de creation du cercle
	 * @param variable nom de la forme
	 * @param split2 la donnee
	 * @return la forme cercle
	 */
    private Forme createCercle( final String variable, final String[] split2){
	 final int trois = 3;
     String[] split = split2[1].split("Cercle");
     if (!split[0].equals("")
             || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
         System.err.println("Commande invalide, parentheses manquantes");
     } else {
         split[1] = split[1].substring(1, split[1].length() - 1);
         split = split[1].split(",");
         if (split.length != trois) {
             System.err.println("Commande invalide, "
                     + split.length + "/" + trois + " arguments");
         } else {
             Point centre;
             int rayon;
             try {
                 centre = new Point(split[0] + "," + split[1]);
                 rayon = Integer.parseInt(split[2]);
                 return new Cercle(variable, centre, rayon);
             } catch (Exception e) {
                 System.err.println("Commande invalide, "
                         + "impossible de creer la forme");
             }
         }
     }
     return null;
   }

     /**
     * interpretation de la commande de creation du carre
     * @param variable nom de la variable 
     * @param split2 la donnee
     * @return la fomre carre
     */
     private Forme createCarre(  final String variable, final String[] split2){
	final int trois = 3;
    String[] split = split2[1].split("Carre");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parentheses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        if (split.length != trois) {
            System.err.println("Commande invalide, "
                    + split.length + "/" + trois + " arguments");
        } else {
            Point point;
            int cote;
            try {
                point = new Point(split[0] + "," + split[1]);
                cote = Integer.parseInt(split[2]);
                return new Carre(variable, point, cote);
            } catch (Exception e) {
                System.err.println("Commande invalide, "
                        + "impossible de creer la forme");
            }
        }
    }
    return null;
  }

    /**
    * interpretation de la commande de creation du rectangle
    * @param variable nom de la variable
    * @param split2 la donnee
    * @return la forme rectangle
    */
    private Forme createRectangle(final String variable, final String[] split2){
	final int quatre = 4;
    String[] split = split2[1].split("Rectangle");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parentheses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        if (split.length != quatre) {
            System.err.println("Commande invalide, "
                    + split.length + "/" + quatre + " arguments");
        } else {
        	Point point;
            int longeur;
            int largeur;
            try {
                final int trois = 3;
                point = new Point(split[0] + "," + split[1]);
                longeur = Integer.parseInt(split[2]);
                largeur = Integer.parseInt(split[trois]);
                return new Rectangle(
                        variable, point, longeur, largeur);
            } catch (Exception e) {
                System.err.println("Commande invalide, "
                        + "impossible de creer la forme");
            }
        }
    }
    return null;
  }
	/**
	 * interpretation dela commande de creation du triangle
	 * @param variable nom de la variable
	 * @param split2 la donnee
	 * @return la forme triangle
	 */
     private Forme createTriangle(final String variable, final String[] split2){
	final int six = 6;
    String[]  split = split2[1].split("Triangle");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parentheses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        if (split.length != six) {
            System.err.println("Commande invalide, "
        + split.length + "/" + six + " arguments");
        }
        Point[] point = {null, null, null};
        try {
            final int trois = 3, quatre = 4, cinq = 5;
            point[0] = new Point(split[0] + "," + split[1]);
            point[1] = new Point(split[2] + "," + split[trois]);
            point[2] = new Point(split[quatre] + "," + split[cinq]);
            return new Triangle(variable, point[0], point[1], point[2]);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Commande invalide, "
                    + "impossible de creer la forme");
        }
    }
    return null;
  }
   
   /**
   * interpretation de la commande groupe
   * @param variable non de la variable
   * @param split2 la donnee
   * @return le Groupe de formes
   */
   private Forme createGroupe(final String variable, final String[] split2){
	String[] split = split2[1].split("Groupe");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parentheses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        return createGroupeComposants(variable, split);
    }
    return null;
  }

   /**
   * interpretation de la commande pour la creation d'une partie composante
   * @param variable nom de la variable
   * @param split la donnee
   * @return le groupe
   */
   private Forme createGroupeComposants(String variable, String[] split) {
    GroupeForme gf = new GroupeForme(variable);
    for (String s : split) {
        Forme f = this.getForme(s);
        if (f != null) {
            gf.ajoutForme(f);
        } else {
            return null;
        }
    }
    return gf;
  }

   /**
   * recupere la forme 
   * @param variable nom de la forme
   * @return la forme
   */
   private Forme getForme(final String variable) {
	 DAOFactoryJDBC factory = new DAOFactoryJDBC();
     DAO<Cercle> cercle = factory.getCercleDAO();
     DAO<Carre> carre = factory.getCarreDAO();
     DAO<Rectangle> rectangle = factory.getRectangleDAO();
     DAO<Triangle> triangle = factory.getTriangleDAO();
     DAO<GroupeForme> grp = factory.getGroupeDAO();
     Forme f = cercle.find(variable);
     if (f == null) {
         f = carre.find(variable);
     }
     if (f == null) {
         f = rectangle.find(variable);
     }
     if (f == null) {
         f = triangle.find(variable);
     }
     if (f == null) {
         f = grp.find(variable);
     }
     if (f == null) {
         System.err.println("Aucune forme n'existe a ce nom : "+ variable);
     }
     factory.close();
     return f;
  }
   
   /**
   * interpretation de la commande de creation de la forme
   * @param cmd2 la commande
   * @return la forme
   */
   private Forme create(final String cmd2){
	String[] split;
    split = cmd2.split("=");
    split[0] = split[0].trim();
    String variable = split[0];
    if (split[0].contains(" ")) {
        System.out.println("Le nom de la variable contient des espaces");
    } else {
        split[1] = split[1].replace(" ", "");
        Forme forme = null;
        if (split[1].contains("Cercle")) {
        	forme = this.createCercle(variable, split);
        } else if (split[1].contains("Carre")) {
            forme = this.createCarre(variable, split);
        } else if (split[1].contains("Rectangle")) {
            forme = this.createRectangle(variable, split);
        } else if (split[1].contains("Triangle")) {
            forme = this.createTriangle(variable, split);
        } else if (split[1].contains("Groupe")) {
            forme = this.createGroupe(variable, split);
        }
        return forme;
    }
    return null;
  }
   
  /**
   * interpretation de la commande de deplacement la forme
   * @param cmd2 la commande
   * @return la coommande de deplacement
   */
   private Command move(final String cmd2){
	final int trois = 3;
    String cmd = cmd2.replace(" ", "");
    String[] split = cmd.split("move");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parentheses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        if (split.length != trois) {
            System.err.println("Commande invalide, "
                    + split.length + "/" + trois + " arguments");
        } else {
            String variable;
            Point deplacement;
            try {
                variable = split[0];
                deplacement = new Point(split[1] + "," + split[2]);
                Forme f = this.getForme(variable);
                if (f != null) {
                    return new DeplacementCommand(f, deplacement);
                }
            } catch (Exception e) {
                System.err.println("Commande invalide");
                e.printStackTrace();
            }
        }
    }
    return null;
  }
   
    /**
    * interpretation de la commande de suppression la forme
    * @param cmd2 la commande
    * @return la commande de suppression
    */
    private Command remove(final String cmd2) {
    String cmd = cmd2.replace(" ", "");
    String[] split = cmd.split("delete");
    if (!split[0].equals("")
            || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
        System.err.println("Commande invalide, parentheses manquantes");
    } else {
        split[1] = split[1].substring(1, split[1].length() - 1);
        split = split[1].split(",");
        ArrayList<Forme> list = new ArrayList<Forme>();
        for (String var : split) {
            Forme forme = this.getForme(var);
            if (forme != null) {
                list.add(forme);
            } else {
                System.err.println("Commande invalide, forme inconnu");
                return null;
            }
        }
        return new SuppressionCommand(list);
    }
    return null;
  }	

   /**
   * interpretation d'une commande
   * @param command commande a interpreter
   * @return la commande
   */
   public Command nextCommand(final String command) {
    if (command.contains("=")) {
        Forme f = this.create(command);
        if (f != null) {
            return new CreationCommand(f);
        }
    } else if (command.contains("move")) {
        return this.move(command);
    } else if (command.contains("delete")) {
        return this.remove(command);
    } else if (command.equals("action")) {
        System.out.println("Liste des actions: \n"
        		+ " Carre :	forme = Ca"
                + "rre((x,y), cote)\n"
                + " Cercle:	"
                + "forme = Cercle((x,y), rayon)\n"
                + " Rectangle :	"
                + " forme = Re"
                + "ctangle((x,y), longueur, largeur)\n"
                + " Triangle :		"
                + "forme = Tr"
                + "iangle((x,y), (x,y), (x,y))\n"
                + " Groupe de formes :		"
                + "forme = Gr"
                + "oupe(forme, ...)\n"
                + "\n"
                + " deplacer une forme ou un groupe :		"
                + "  move(forme"
                + ", (x,y))\n"
                + " supprimer une forme ou un groupe :		"
                + " delete(forme, ...)"
                +"\n\n\n       executez l'action en respectant le syntaxe :   \n");
    } else if (!command.equalsIgnoreCase("quit")) {
        System.err.println("Commande non reconnue");
    }
    return null;
    }

    /**
    * verifier si la forme existe bien dans un groupe
    * @param forme forme a verifier
    * @return vrai si la forme existe dans le groupe
    */
   private boolean estDansUnGroupe(final Forme forme) {
    Connection con = FormeandRelation.getConnect();
    try {
        PreparedStatement prepare = con.prepareStatement(
                "SELECT * FROM Relation WHERE nomForme = ?");
    
        prepare.setString(1, forme.getNom());
        ResultSet result = prepare.executeQuery();
        boolean b = result.next();
        con.close();
        return b;
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            con.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return false;
    }
  }

  /**
   * affichage des formes
   */
   public  void afficheDraw() {
    DAOFactoryJDBC factory = new DAOFactoryJDBC();
    DAO<Cercle> cercle = factory.getCercleDAO();
    DAO<Rectangle> rectangle = factory.getRectangleDAO();
    DAO<Triangle> triangle = factory.getTriangleDAO();
    DAO<Carre> carre = factory.getCarreDAO();
    DAO<GroupeForme> groupe = factory.getGroupeDAO();
    ArrayList<Forme> formes = new ArrayList<Forme>();
    formes.addAll(cercle.show());
    formes.addAll(rectangle.show());
    formes.addAll(triangle.show());
    formes.addAll(carre.show());
    formes.addAll(groupe.show());
    for (Forme forme : formes) {
    	
        if (!this.estDansUnGroupe(forme)) {
            forme.affiche();
        }
    }
    factory.close();
}
}

