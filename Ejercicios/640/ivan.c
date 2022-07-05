#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TAM 11
#define CERO 48

int main(int argc, char *argv[]){
    register int i, j, acarreo;
    int empezar, D, batch = 59049;
    long N;
    char str[TAM];
    
    while(1){
        //Reiniciamos variables
        for(i=0; i<11; i++){
            str[i] = CERO;
        }
        empezar = 0;
        //Se leen los valores
        scanf("%ld %d", &N, &D);
        //Condición de fin
        if(N == 0 && D == 0) break;
        //Empieza funcionalidad
        for(i=0; i<N/batch; i++){
                acarreo=9;
                //Difundimos acarreo
                for(j=5; acarreo && j<TAM; j++){
                    //Le sumamos uno
                    str[j]+= 1 + (str[j]-47 == D);
                    //Si se pasa de 9 hay acarreo
                    if(str[j]-48 > 9){
                        acarreo = 1;
                        str[j] = CERO;
                    } else{
                        acarreo = 0;
                    }
                }
        }

        for(i=0; i<N-batch*(N/batch); i++){
            acarreo = 0;
            //Le sumamos uno, teniendo en cuenta el dígito prohibido
            str[0]+= 1 + (str[0]-47 == D);
            //Si se pasa de 9, hay acarreo
            if(str[0]-48 > 9){
                str[0] = CERO;
                acarreo=1;
                //Difundimos acarreo
                for(j=1; acarreo && j<TAM; j++){
                    //Le sumamos uno
                    str[j]+= 1 + (str[j]-47 == D);
                    //Si se pasa de 9 hay acarreo
                    if(str[j]-48 > 9){
                        acarreo = 1;
                        str[j] = CERO;
                    } else{
                        acarreo = 0;
                    }
                }
            }
            
        }

        //Fin funcionalidad
        //Se imprime
        for(i=TAM-1; i>-1; i--){
            if(str[i] != CERO){
                empezar = 1;
            }
            if(empezar == 0) continue;
            printf("%c", str[i]);
        }
        printf("\n");
    }
}