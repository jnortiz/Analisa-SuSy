#include <stdio.h>

/* Converter Graus                                                                                         *
 * Objetivo:    Dado um valor em graus, retorna o equivalente em movimentos de um ponteiro no rel—gio      *
 * Sa’da:       Nœmero de movimentos de um ponteiro do rel—gio                                             *
 * Par‰metros:                                                                                             *
 *      - unsigned int entrada: Valor em graus a ser convertido                                            *
 */
int booleana=3;
int tempo;
int rainha_criquete=0;
char p[600];
int tamanho;
int h;

unsigned int converter_graus(unsigned int entrada){
    int tempo;
    tempo=entrada/6;
    return tempo;
}

/* Qual Personagem                                                                  *
 * Objetivo:    Determinar qual personagem Ž encontrado em determinado momento      *
 * Sa’da:       Um caractere que representa um personagem                           *
 * Par‰metros:                                                                      *
 *      - unsigned int minutos: Posi‹o do ponteiro de minutos                      *
 */
    char qual_personagem(unsigned int minutos){
        if((minutos==7) || (minutos==30)) { p[h] ='R'; }
        if((minutos==23) || (minutos==25)) { p[h] ='V'; }
        if((minutos==9) || (minutos==48)) { p[h] ='L'; }
        if((minutos==34) || (minutos==55)) { p[h] ='M'; }
        if((minutos==15) || (minutos==50)) { p[h] ='D'; }
        if(minutos==42) { p[h] ='C'; }
    return p[h] ;
}

/* Rainha                                                                                   *
 * Objetivo:    Determinar se Alice ganha ou perde a partida de cr’quete com a rainha       *
 * Sa’da:       Booleana: 0, caso n‹o; 1, caso sim;                                         *
 * Par‰metros:                                                                              *
 *      - unsigned int tempo: O tempo decorrido desde o in’cio da jornada de Alice          *
 */
int rainha(unsigned int tempo){
    if(tempo>48) {booleana=0; }
    if(tempo<=48) {booleana=1; }
    rainha_criquete=1;
    return booleana;
}

/* Valete                                                                                               *
 * Objetivo:    Determinar se Alice Ž decapitada                                                        *
 * Sa’da:       Booleana: 0, caso n‹o; 1, caso sim;                                                     *
 * Par‰metros:                                                                                          *
 *      - unsigned int jogou_criquete:  Booleano que diz se Alice j‡ jogou uma partida de criquete      *
 *      - unsigned int ganhou_criquete: Booleano que diz se Alice j‡ ganhou uma partida de criquete     *
 */
int valete(unsigned int jogou_criquete, unsigned int ganhou_criquete){
    if(rainha_criquete==1) {
            if(booleana==1) {booleana=1; }
            if( booleana==0) { booleana=0;}
    }
    return booleana;
}


/* Coelho                                                                                         *
 * Objetivo:    Determinar se Alice sai com o coelho do Pa’s das Maravilhas                       *
 * Sa’da:       Booleana: 0, caso n‹o; 1, caso sim;                                               *
 * Par‰metros:                                                                                    *
 *      - unsigned int jogou_criquete: Booleano que diz se Alice j‡ jogou uma partida de cr’quete *
 */
int coelho(unsigned int jogou_criquete){
 if(booleana!=3) { booleana=1; }
 if(booleana==3) { booleana=0; }
 return booleana;

}

/* Imprimir Personagens                                                             *
 * Objetivo:    Imprimir uma lista de personagens e retornar seu tamanho atual      *
 * Sa’da:       O tamanho do vetor de personagens ap—s a impress‹o                  *
 * Par‰metros:                                                                      *
 *      - char personagens[]: Um vetor com uma lista de personagens a ser impressa  *
 *      - int numero:         O tamanho atual do vetor                              *
 */
int imprimir_personagens(char personagens[], int numero){
    while(p[h]!='L'){ h++; tamanho=h; }
    tamanho = numero - tamanho;
    return tamanho;

}

/* Imprimir Hor‡rio                                                               *
 * Objetivo:    Imprimir um hor‡rio no formato HH:MM:SS dado um valor de segundos *
 * Sa’da:       _void_                                                            *
 * Par‰metros:                                                                    *
 *      - unsigned int segundos: Nœmero de segundos a ser impresso                *
 */
void imprimir_horario(unsigned int segundos){
printf("%02d %02d %02d", segundos/3600,(segundos/60)%60,segundos%60);
return;
}


int main() {
    int S=0,S1=0,segundos=0,minutos=0,horas=0;
    int segundos1=0,minutos1=0;
    int M[200000],M1=0;
    int vida=0, dodo=1,aux=0;
    int i,t=0,j,m,ad=0;
    int rainha=600;
    int coelho=600;
    int valete=600;
    char x;
    char V[6000];
    M1 =S1 =0;
    S = M[0] =0;
    horas=0;
    i=1;
    j=0;

        while(vida==0) {
                scanf("%d %c",&t ,&x);

            if(x=='S') {segundos1 = (t/6) + segundos1; }
            if(x=='s') {segundos1 = t + segundos1; }
            if(x=='M') {minutos1 = (t/6) + minutos1; }
            if(x=='m') {minutos1 = t + minutos1; }

            S1=segundos1%60;
            M1=(minutos1)+(segundos1/60);
            horas=M1/60;



            if(x=='S') {segundos = (t/6) + segundos; }
            if(x=='s') {segundos = t + segundos; }
            if(x=='M') {minutos = (t/6) + minutos; }
            if(x=='m') {minutos = t + minutos; }



            M[i]=((minutos)+(segundos/60))%60;



          if(((M[i]==7 ) && (M[i-1]!=7)) || ((M[i]==30 ) && (M[i-1]!=30))) {

                    if(dodo==1){
                        V[j]='R'; j++;
                        if((M1<42) && (horas==0)) { aux=-1; }
                        if(M1>=42)  { aux=1; }

                    }
                    if(dodo==-1){ dodo=1; }
            }


          if(((M[i]==23 ) && (M[i-1]!=23)) || ((M[i]==25 ) && (M[i-1]!=25))) {

                    if(dodo==1){
                    V[j]='V';j++;
                    }

                    if(dodo==-1){ dodo=1; }
            }

             if(((M[i]==9 ) && (M[i-1]!=9)) || ((M[i]==48 ) && (M[i-1]!=48))) {

                    if(dodo==1){ V[j]='L';}

                    if(dodo==-1){ dodo=1; }
            }

            if(((M[i]==34 ) && (M[i-1]!=34)) || ((M[i]==55 ) && (M[i-1]!=55))) {

                    if(dodo==1){ V[j]='M';segundos=0; minutos=0;  j++;}

                    if(dodo==-1){ dodo=1; }
            }


            if(((M[i]==15 ) && (M[i-1]!=15)) || ((M[i]==50 ) && (M[i-1]!=50))) {

                    if((dodo==-1) && (ad==0)) {dodo=1; ad=1; }

                    if((dodo==1) && (ad==0)) { V[j]='D';dodo=-1; j++; ad=1; }

                    ad=0;

            }

            if(((M[i]==42 ) && (M[i-1]!=42))) {

                    if(dodo==1){
                            V[j]='C';j++;
                    }
                    if(dodo==-1){ dodo=1; }
            }

                 /* printf("%02d:%02d \n",M[i],S); */



            if(V[j-1]=='R') {rainha=j-1; }


            if(V[j-1]=='V') {valete=j-1; }


            if(V[j-1]=='C') {coelho=j-1; }

            if(aux==1){ valete=0;}



           if(V[j]=='L'){
                    while(m<=j) {
                        printf("%c ",V[m]);
                    m++;
                    }
                    printf("\n");
                     m = j;
                     V[j]='K';

            }


           if((rainha!=600) && (coelho!=600) && (rainha<coelho)) {
            printf("SAIU\n");
            printf("%02d:%02d:%02d\n",M1/60,M1%60,S1%60);
           vida=1;
           }

           if((rainha!=600) && (valete!=600) && (rainha<valete)) {
            printf("MORREU\n");
            printf("%02d:%02d:%02d\n",M1/60,M1%60,S1%60);
            vida=1;
           }

        i++;
    }

return 0;
}
