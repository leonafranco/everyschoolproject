package com.example.tp1;

import java.io.Serializable;
import java.util.ArrayList;

public class GerePlanos implements Serializable {

   private ArrayList<PlanoAlimentar> listaPlano = new ArrayList<>();

   public boolean adicionarRefeicao(PlanoAlimentar aPlano) {

      if (this.listaPlano != null) {

         if (this.listaPlano.size() > 0) {

            for (PlanoAlimentar plano : this.listaPlano) {

                  if (plano.getHora().equalsIgnoreCase(aPlano.getHora())) {
                     return false;
                  } else {
                     this.listaPlano.add(aPlano);
                     return true;
                  }
            }
         } else {
            this.listaPlano.add(aPlano);
            return true;
         }
      }
      return false;
   }

   public ArrayList<PlanoAlimentar> listaPlanos() {
      return this.listaPlano;
   }

   public boolean verificaHora(PlanoAlimentar aPlano) {
      if (this.listaPlano != null) {

         if (this.listaPlano.size() > 0) {

            for (PlanoAlimentar plano : this.listaPlano) {

               if (plano.getHora().equalsIgnoreCase(aPlano.getHora())) {
                  return false;
               } else {
                  return true;
               }
            }
         } else {
            return true;
         }
      }

      return false;
   }
}
