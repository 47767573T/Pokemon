import generated.NombreType;
import generated.PokedexType;
import generated.PokemonType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.*;
import java.io.File;
import java.util.Scanner;

/**
 * Created by 47767573t on 27/10/15.
 */
public class Pokedex {

    static String ruta = "/home/47767573t/git/IdeaWorkSpace/M06/UF01/Pokemon/src";
    public static void main (String[] args){

        Scanner scn = new Scanner(System.in);

        File filePokeDex = new File(ruta+"/pokemons.xml");

        Boolean introducir = true;


        String nombre = "";
        String classe = "";
        String pv = "";
        String atq1 = "";
        String atq2 = "";
        String etapa = "";

        NombreType n;
        PokemonType pokemon;


        while (introducir) {
            //Preguntamos por pantalla las caracteristicas del pokemon
            System.out.println("Indique nombre del pokemon: ");
            nombre = scn.next();

            System.out.println("Indique clase del pokemon: ");
            classe = scn.next();

            System.out.println("Indique PV del pokemon: ");
            pv = scn.next();

            System.out.println("Indique ataque1 del pokemon: ");
            atq1 = scn.next();

            System.out.println("Indique ataque2 del pokemon: ");
            atq2 = scn.next();

            System.out.println("Indique etapa del pokemon: ");
            etapa = scn.next();

            //Rellenamos los valores del tipo nombre
            n  = new NombreType();
            n.setValue(nombre);
            n.setClasse(classe);

            //Rellenamos los valores del tipo pokemon
            pokemon = new PokemonType();
            pokemon.setNombre(n);
            pokemon.setPV(pv);
            pokemon.setAtaque1(atq1);
            pokemon.setAtaque2(atq2);
            pokemon.setEtapa(etapa);



            try{
                JAXBContext jaxbContext = JAXBContext.newInstance(PokedexType.class);
                Unmarshaller unM = jaxbContext.createUnmarshaller();
                PokedexType pt = (PokedexType)unM.unmarshal(filePokeDex);
                pt.getPokemon().add(pokemon);

                PokedexType pdType = (PokedexType)unM.unmarshal(filePokeDex);

                System.out.println("1");

                pdType.getPokemon().add(pokemon);
                //Guardamos el pokemon en xml nuevo
                guardar(pdType);

            } catch (JAXBException e) {
                e.printStackTrace();
            }
            boolean malaRespuesta = true;

            while (malaRespuesta) {

                System.out.println("Desea continuar introduciendo pokemons? \n1. si \n2. no");

                String respuesta = scn.nextLine();

                if (respuesta.equals("1")){
                    System.out.println("sigamos...");
                    malaRespuesta = false;
                }
                else if (respuesta.equals("2")){
                    introducir = false;
                    malaRespuesta = false;
                }
                else System.out.println("");
            }
        }

    }

    /**
     * Guardar pokemons en nuevo Archivo
     * @param pd
     */
    public static void guardar (PokedexType pd){


        File ficheroNew = new File (ruta+"/newPokemons.xml");

        try {
            JAXBContext jc = JAXBContext.newInstance(PokedexType.class);
            Marshaller marsh = jc.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marsh.marshal(pd, ficheroNew);
            marsh.marshal(pd,System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }



}
