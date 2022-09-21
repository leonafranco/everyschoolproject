#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ALUNOS 50
#define MAX_TRABALHOS 20
char alunos_db[] = {"alunos_db.txt"};
struct trabalhos{
    float peso;
    float notas;
    int contador;
    int ativo;
};

typedef struct aluno{
    int num_aluno;
    char nome[50];
    int ativo;
    struct trabalhos trab_pratico[MAX_TRABALHOS];

} Aluno;

Aluno alunos [MAX_ALUNOS];

void escolha_menu_principal(int escolha);
void inserir_trabalhos_praticos();
void criar_novo_aluno();
void remover();
void alterar_info();
void menu_principal();
void ajuda();
void sair();
void error();
void vernotas();
void media_aluno();
void media_trabalho();
void media_turma();
void guarda_dados();
void carrega_dados();

int main() {
    carrega_dados();
    menu_principal();
    return 0;
}

void menu_principal() {

    while (1) {
        printf("-----------------------------------------------------\n");
        printf("| 1 - Criar um novo Alunos                          |\n");
        printf("| 2 - Inserir trabalhos práticos                    |\n");
        printf("| 3 - alterar info                                  |\n");
        printf("| 4 - Remover aluno                                 |\n");
        printf("| 5 - Apresentar as notas de todos os alunos        |\n");
        printf("| 6 - ver media                                     |\n");
        printf("| 7 - Apresentar Média número do aluno              |\n");
        printf("| 8 - Apresentar Média nome do aluno                |\n");
        printf("| 9 - Aprensentar pesos e médias trabalhos práticos |\n");
        printf("| 10- Apresentar Média da turma                     |\n");
        printf("| 11- Ajuda                                         |\n");
        printf("| 12- Sair                                          |\n");
        printf("| 13- Guardar Dados                                 |\n");
        printf("-----------------------------------------------------\n");
        int opcao;
        scanf(" %d", &opcao);
        if (opcao == 12)
            break;
        escolha_menu_principal(opcao);
    }
    sair();
}


//! alinea a) criar um novo aluno 1-
void criar_novo_aluno() {
    int num_aluno;
    char nome[50];
    int op;

    while(1) {

        getchar();

        printf("Qual é o nome do Aluno?\n");
        fgets(nome, sizeof(nome), stdin);
        strtok(nome, "\n");

        
        printf("Qual o número do Aluno?\n");
        scanf("%d", &num_aluno);

        for (int i = 0; i < MAX_ALUNOS; i++)  {
            if (alunos[i].ativo == 0) {
                alunos[i].num_aluno = num_aluno;
                strcpy(alunos[i].nome, nome);
                alunos[i].ativo = 1;
                printf("\nUtilizador criado com sucesso!\n");
                break; //sair do ciclo 
            }else if (alunos[MAX_ALUNOS].ativo == 1)
            {
                printf("\n\nImpossivel criar novo aluno\n\n");
            }
            
        }

        printf("Deseja adicionar mais algum aluno?\n\n Sim - 1 \n Não - 2 \n\n");
        scanf("%d", &op);


        

        if(op == 2)
            break;
    }
}

//! alinea b) e c) 2-
void inserir_trabalhos_praticos() {
    int num_aluno;
    int num_nota;
    float nota;
    float peso;
    int count;
    int op;
    int inserir_alterar;

    while(1) {

        count = 1;

        getchar();
        printf("Deseja Inserir ou alterar notas?\n1- Inserir 2- Alterar\n");
        scanf("%d", &inserir_alterar);
        

        printf("Qual é o numero do Aluno?\n");
        scanf("%d", &num_aluno);

        while(1) {
            printf("Qual o valor da nota? 0-20\n");
            scanf("%f", &nota);
            if (nota >= 0.0 && nota <= 20.0)
                break;
                else {
                    printf("\nIntroduza uma nota de 0 a 20\n");
                }
                
        }

        while(1) {
            printf("Quanto vale a nota? 0-100\n");
            scanf("%f", &peso);
            if (peso >= 0 && peso <= 100)
                break;
            else {
                printf("\nIntroduza um peso de 0 a 100\n");
            }
        }

        switch (inserir_alterar) {
            case 1:
                for (int i = 0; i < MAX_ALUNOS; i++)  {
                    if (alunos[i].num_aluno == num_aluno) {
                        for(int f = 0; f < MAX_TRABALHOS; f++) {
                            if(alunos[i].trab_pratico[f].ativo == 0) {
                                alunos[i].trab_pratico[f].notas = nota;
                                alunos[i].trab_pratico[f].peso = peso;
                                alunos[i].trab_pratico[f].ativo = 1;
                                count += f;
                                alunos[i].trab_pratico[f].contador = count;
                                printf("\nTrabalho inserido com sucesso!\n");
                                break;
                            }
                        }
                        break;
                    }else if (alunos[i].num_aluno != num_aluno && i == MAX_ALUNOS-1)
                    {
                        printf("\nNão existe nenhum aluno com o número %d\n", num_aluno);
                    }
                    
                }
            break;

            case 2:
                printf("Qual o número da nota:\n");
                scanf("%d", &num_nota);
                for (int i = 0; i < MAX_ALUNOS; i++)  {
                    if (alunos[i].num_aluno == num_aluno) {
                        for(int f = 0; f < MAX_TRABALHOS; f++) {
                            if(alunos[i].trab_pratico[f].contador == num_nota) {
                                alunos[i].trab_pratico[f].notas = nota;
                                alunos[i].trab_pratico[f].peso = peso;
                                printf("\nNota Alterada com Sucesso!\n");
                                break;
                            }else if (alunos[i].num_aluno == num_aluno && alunos[i].trab_pratico[f].contador != num_nota && f == MAX_TRABALHOS-1)
                            {
                                printf("\nNão existe nenhuma nota com o número %d\n", num_nota);
                            }
                            
                        }
                        break;
                    }else if (alunos[i].num_aluno != num_aluno && i == MAX_ALUNOS-1)
                    {
                        printf("\nNão existe nenhum aluno com o número %d\n", num_aluno);
                    }
                    
                }
                break;
        
        
            default:
                printf("insira uma opção valida");
                break;
        }


        printf("\n\nDeseja adicionar mais alguma nota?\n\n Sim - 1 \n Não - 2 \n\n");
        scanf("%d", &op);

        if(op == 2)
            break;
    }
}

//! alinea d) alterar nome e trabalho pratico 3-
void alterar_info() {

    int num_aluno;
    float peso;
    int num;
    char nome[50];
    int op_principal;
    int op_secundaria;

    while(1) {

        printf("\n\nDeseja altera nome ou peso da nota? \n1- nome 2- peso\n");
        scanf("%d", &op_principal);

        printf("Qual o número do aluno?\n");
        scanf("%d", &num_aluno);

        if(op_principal == 1) {
            getchar();
            printf("Qual o nome para qual deve alterar?\n");
            fgets(nome, sizeof(nome), stdin);
            
            for (int i = 0; i < MAX_ALUNOS; i++)  {
                if (alunos[i].num_aluno == num_aluno) {
                    strcpy(alunos[i].nome, nome);
                    printf("\n\nNome alterado com sucesso\n\n");
                    break;
                }else if (alunos[i].num_aluno != num_aluno && i == MAX_ALUNOS-1)
                {
                    printf("\n\n Não existe nenhum aluno com o número %d", num_aluno);
                }
                
            }
        }else if (op_principal == 2) {
            printf("Qual o numero da prova que deseja alterar?\n");
            scanf("%d", &num);

            while(1) {
                printf("Quanto vale a nota? 0-100\n");
                scanf("%f", &peso);
                if (peso >= 0 && peso <= 100)
                    break;
                else {
                    printf("\nIntroduza um peso de 0 a 100\n");
                }
            }

            for(int i = 0; i < MAX_ALUNOS; i++ ) {
                if(alunos[i].num_aluno == num_aluno) {
                    for(int f = 0; f < MAX_TRABALHOS; f++) {
                        if(alunos[i].trab_pratico[f].contador == num) {
                            alunos[i].trab_pratico[f].peso = peso;
                            break;
                        }else if (alunos[i].num_aluno == num_aluno && alunos[i].trab_pratico[f].contador != num && f == MAX_TRABALHOS-1)
                        {
                            printf("\nNão existe nenhuma nota com o indice %d\n", num);
                        }
                    }
                    break;
                }else if (alunos[i].num_aluno != num_aluno && i == MAX_ALUNOS-1)
                {
                    printf("\nNão existe nenhum aluno com o número %d\n", num_aluno);
                }
                
            }
        }else
        {
            printf("\n\nIntroduza uma opção valida!\n\n");
        }
        

        printf("\n\nDeseja alterar mais algum aluno ou?\n\n Sim - 1 \n Não - 2 \n\n");
        scanf("%d", &op_secundaria);

        if(op_secundaria == 2)
            break;

    }
    
}

//! Remover e) alunos ou notas 4-
void remover() {
    int num_aluno;
    int num_trabalho;
    int op_principal;
    int op_secundaria;
    int count = 1;


    while(1) {
        printf("Deseja alterar alunos ou notas de alunos?  \n1- Aluno 2- Notas\n");
        scanf("%d", &op_principal);

        printf("Qual o num do aluno que deseja remover?\n");
        scanf("%d", &num_aluno);

        if(op_principal == 1) {
            for(int i = 0; i < MAX_ALUNOS; i++) {
                if(alunos[i].num_aluno == num_aluno) {
                    alunos[i].ativo = 0;
                    printf("\n\nAluno removido com sucesso!\n\n");
                    for(int f = 0; f < MAX_TRABALHOS; f++) {
                        alunos[i].trab_pratico[f].ativo = 0;
                    }
                    break;
                }else if (alunos[i].num_aluno != num_aluno && i == MAX_ALUNOS-1)
                {
                    printf("\n\nNão existe nenhum aluno com o número %d", num_aluno);
                }
                
            }    
        }else if (op_principal == 2){  
            printf("Qual o número do trabalho que seja remover?\n");
            scanf("%d", &num_trabalho);     
            for(int i = 0; i < MAX_ALUNOS; i++) {
                if(alunos[i].num_aluno == num_aluno) {
                    for(int f = 0; f < MAX_TRABALHOS; f++) {
                        if(alunos[i].trab_pratico[f].contador == num_trabalho) {
                            alunos[i].trab_pratico[f].ativo = 0;
                            printf("\n\nTrabalho removido com sucesso!\n\n");
                            break;
                        }else if (alunos[i].num_aluno == num_aluno && alunos[i].trab_pratico[f].contador != num_trabalho && f == MAX_TRABALHOS-1)
                        {
                            printf("\n\nNão existe nenhum trabalho com o indice %d", num_trabalho);
                        }
                        
                    }
                    break;
                }else if (alunos[i].num_aluno != num_aluno && i == MAX_ALUNOS-1)
                {
                    printf("\n\nNão existe nenhum aluno com o número %d", num_aluno);
                }
                
            }

            for(int i = 0; i < MAX_ALUNOS; i++) {
                if(alunos[i].num_aluno == num_aluno) {
                    for(int f = 0; f < MAX_TRABALHOS; f++ ) {
                        if(alunos[i].trab_pratico[f].ativo == 1) {
                            alunos[i].trab_pratico[f].contador = count;
                            count++;
                        }
                    }
                }
            }

        }else
        {
            printf("\n\n Introduza uma opção válida!\n\n");
        }
    
        printf("\n\nDeseja alterar mais algum aluno ou?\n\n Sim - 1 \n Não - 2 \n\n");
        scanf("%d", &op_secundaria);

        if (op_secundaria == 2)
        break;
        

    }
    
    
}

//! f) apresentar as notas de todos os alunos 5-
void ver_notas() {
    
    printf("\n\nCabeçalho aluno\n\n");

    for(int i = 0; i< MAX_ALUNOS; i++) {
        if(alunos[i].ativo == 1) {
            printf("Aluno: %s Número %d \n", alunos[i].nome, alunos[i].num_aluno);
            for(int f = 0; f < MAX_TRABALHOS; f++){
                if(alunos[i].trab_pratico[f].ativo == 1){
                    printf("Peso: %.0f Nota: %0.2f Número: %d \n",alunos[i].trab_pratico[f].peso, alunos[i].trab_pratico[f].notas, alunos[i].trab_pratico[f].contador);
                }
            }
            printf("\n");
        }
    }
}

//! g) ver media de todos os alunos  6-
void media_aluno() {

    float media_alunos;
    float media_total;
    float count_trabalhos = 0;

    printf("\n\n");

    for(int i = 0; i< MAX_ALUNOS; i++) {
        if(alunos[i].ativo == 1) {
            printf("Aluno: %s | Número de aluno:%d \n", alunos[i].nome, alunos[i].num_aluno);
            for(int f = 0; f < MAX_TRABALHOS; f++){
                if(alunos[i].trab_pratico[f].ativo == 1){
                    media_alunos = (alunos[i].trab_pratico[f].peso/100 * alunos[i].trab_pratico[f].notas);
                    media_total += media_alunos;
                    count_trabalhos++;
                }
            }
            media_total = media_total/count_trabalhos;
            printf("Média do aluno: %.2f \n\n ", media_total);
            media_alunos = 0;
            media_total = 0;
            count_trabalhos =0;
        }
    }
}

//! Alinea h) aprensetar media e notas do aluno com base do seu numero 7-
void media_num_aluno() {

    int num_aluno;
    float media_alunos;
    float media_total;
    float count_trabalhos = 0;

    printf("Qual o número do aluno?\n");
    scanf("%d", &num_aluno);


    for(int i = 0; i < MAX_ALUNOS; i++) {
        if(alunos[i].num_aluno == num_aluno) {
            printf("\n\nNome: %s \nNúmero: %d \nNotas:\n", alunos[i].nome, num_aluno);
            for(int f = 0; f < MAX_TRABALHOS; f++){
                if(alunos[i].trab_pratico[f].ativo == 1){
                    printf("Peso: %.0f Nota: %0.2f Número: %d \n",alunos[i].trab_pratico[f].peso, alunos[i].trab_pratico[f].notas, alunos[i].trab_pratico[f].contador);
                    media_alunos = (alunos[i].trab_pratico[f].peso/100 * alunos[i].trab_pratico[f].notas);
                    media_total += media_alunos;
                    count_trabalhos++;
                }
            }
            media_total = media_total/count_trabalhos;
            printf("Média do aluno: %.2f \n\n ", media_total);
            media_alunos = 0;
            media_total = 0;
        }
    }
}

//! Alinea i) apresentar media e notas 
//* fala colocar + de 1
void media_nome_aluno() {

    char nome[50];
    float media_alunos;
    float media_total;
    float count_trabalhos = 0;
    char *temp;

    getchar();

    printf("Qual o nome do aluno?\n");
    fgets(nome, sizeof(nome), stdin);
    strtok(nome, "\n");

    for(int i = 0; i < MAX_ALUNOS; i++) { 
        if(strcmp(nome, alunos[i].nome) == 0 && alunos[i].ativo == 1) { 
            printf("\nNome: %s \nNúmero: %d \nNotas:\n", alunos[i].nome, alunos[i].num_aluno);
            for(int f = 0; f < MAX_TRABALHOS; f++){
                if(alunos[i].trab_pratico[f].ativo == 1){
                    printf("%.2f \n", alunos[i].trab_pratico[f].notas);
                    media_alunos = (alunos[i].trab_pratico[f].peso/100 * alunos[i].trab_pratico[f].notas);
                    media_total += media_alunos;
                    count_trabalhos++;
                }
            }
            media_total = media_total/count_trabalhos;
            printf("Média do aluno: %.2f \n\n ", media_total);
            media_alunos = 0;
            media_total = 0;
        }
    }

}

//! alinea J) apresentar o pesos e medias do trabalhos praticos 
void media_trabalho() {

    float media_alunos;
    float media_total;
    float count_alunos = 0;
    float notas[MAX_TRABALHOS];
    int num_trabalho = 1;
    float media_teste;
    int ativo[MAX_TRABALHOS];



    for(int i = 0; i< MAX_ALUNOS; i++) {
        if(alunos[i].ativo == 1) {
            count_alunos++;
            for(int f = 0; f < MAX_TRABALHOS; f++){
                if(alunos[i].trab_pratico[f].ativo == 1){
                    media_teste = (alunos[i].trab_pratico[f].peso/100 * alunos[i].trab_pratico[f].notas);
                    notas[f] += media_teste;
                    ativo[f] = 1;
                }
            }
        }
    }
    for (int i = 0; i < MAX_TRABALHOS; i++) {
        notas[i] = notas[i]/count_alunos;
        num_trabalho = i;
        num_trabalho++;
        if(ativo[i] == 1) {
            printf("Média trabalho nº %d: %.2f\n",num_trabalho, notas[i]);
        }
    }
    
}

//! alinea K) apresentar a media da turma
void media_turma() {

    float media_alunos;
    float media_total;
    float count_trabalhos;
    float count_alunos = 0;
    float media_turma = 0;


    for(int i = 0; i < MAX_ALUNOS; i++) {
        if(alunos[i].ativo == 1) {
            count_alunos++;
            count_trabalhos = 0;
            media_alunos = 0;
            media_total = 0;
            for(int f = 0; f < MAX_TRABALHOS; f++){
                if(alunos[i].trab_pratico[f].ativo == 1){
                    media_alunos = (alunos[i].trab_pratico[f].peso/100 * alunos[i].trab_pratico[f].notas);
                    media_total += media_alunos;
                    count_trabalhos++;;
                }
            }
            media_total = media_total/count_trabalhos;
            media_turma += media_total;
        }
    }
    media_turma = media_turma/count_alunos;
    printf("A Média da turma é %.2f\n\n", media_turma);
}

void escolha_menu_principal(int escolha) {
    
    switch(escolha) {
        case 1:
            criar_novo_aluno();
            break;

        case 2:
            inserir_trabalhos_praticos();
            break;

        case 3:
            alterar_info();
            break;

        case 4:
            remover();
            break;
        
        case 5:
            ver_notas();
            break;

        case 6:
            media_aluno();
            break;

        case 7:
            media_num_aluno();
            break;

        case 8:
            media_nome_aluno();
            break;

        case 9:
            media_trabalho();
            break;

        case 10:
            media_turma();
            break;

        case 11:
            ajuda();
            break;

        case 13:
            guarda_dados();
            break;

        default:
            error();
            break;
    }
}


void sair() {
    guarda_dados();
    printf("\n\nEste trabalho foi realizado por Leonardo Menezes Franco\n");
    printf("Muito Obrigado por utilizar\n\n");
}

void error() {
    printf("\n\n introduza um numero correto \n\n");
}

void ajuda() {
    printf("\n\n utilize o teclado para navegar dentro dos menus! \n\n");
}

void guarda_dados() {
    FILE *g;

    g = fopen(alunos_db, "w");
    if (g == NULL) {
        printf("Erro ao guardar dados.\n");
        fclose(g);
    }
    else {
        for(int i = 0; i < MAX_ALUNOS; i ++) {
            if (alunos[i].ativo == 1) {
                fprintf(g, "%s, %d, %d, ", alunos[i].nome, alunos[i].ativo, alunos[i].num_aluno);
                for(int f = 0; f < MAX_TRABALHOS; f++) {
                    fprintf(g, "%f, %f, %d, %d,", alunos[i].trab_pratico[f].peso, alunos[i].trab_pratico[f].notas, alunos[i].trab_pratico[f].ativo, alunos[i].trab_pratico[f].contador);
                }
                //fprintf(g,"\n");
            }
        }
        printf("\n\nFicheiro guardado com sucesso!\n\n");
        fclose(g);
    }

}

void carrega_dados() {
    FILE *g;

    g = fopen(alunos_db, "r");
    if (g == NULL) {
        printf("Erro ao carregar dados.\n");
        fclose(g);
    }
    else {
        char nome[50];
        int num_aluno;
        int ativo;
        float nota[MAX_TRABALHOS];
        float peso[MAX_TRABALHOS];
        int contador[MAX_TRABALHOS];
        int ativo_trab[MAX_TRABALHOS];
        int i = 0;

        while (1)
        {   //!isto é o pedaço de codigo mais nojento que alguma vez vou escrever na minha vida

            fscanf(g, "%50[^,], %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d, %f, %f, %d, %d,", nome, &ativo, &num_aluno, &peso[0], &nota[0], &ativo_trab[0], &contador[0] , &peso[1], &nota[1], &ativo_trab[1], &contador[1] , &peso[2], &nota[2], &ativo_trab[2], &contador[2], &peso[3], &nota[3], &ativo_trab[3], &contador[3] , &peso[4], &nota[4], &ativo_trab[4], &contador[4] , &peso[5], &nota[5], &ativo_trab[5], &contador[5], &peso[6], &nota[6], &ativo_trab[6], &contador[6] , &peso[7], &nota[7], &ativo_trab[7], &contador[7] , &peso[8], &nota[8], &ativo_trab[8], &contador[8], &peso[9], &nota[9], &ativo_trab[9], &contador[9] , &peso[10], &nota[10], &ativo_trab[10], &contador[10] , &peso[11], &nota[11], &ativo_trab[11], &contador[11], &peso[12], &nota[12], &ativo_trab[12], &contador[12] , &peso[13], &nota[13], &ativo_trab[13], &contador[13] , &peso[14], &nota[14], &ativo_trab[14], &contador[14], &peso[15], &nota[15], &ativo_trab[15], &contador[15] , &peso[16], &nota[16], &ativo_trab[16], &contador[16] , &peso[16], &nota[17], &ativo_trab[17], &contador[17], &peso[18], &nota[18], &ativo_trab[18], &contador[18] , &peso[19], &nota[19], &ativo_trab[19], &contador[19]);
            if (feof(g))
                break;

            
                strcpy(alunos[i].nome, nome);
                alunos[i].ativo = ativo;
                alunos[i].num_aluno = num_aluno;
                for(int f = 0; f < MAX_TRABALHOS; f++) {
                    alunos[i].trab_pratico[f].peso = peso[f];
                    alunos[i].trab_pratico[f].notas = nota[f];
                    alunos[i].trab_pratico[f].ativo = ativo_trab[f];
                    alunos[i].trab_pratico[f].contador = contador[f];
                }
            
            i++;
            
        }
        fclose(g);
    }
}


