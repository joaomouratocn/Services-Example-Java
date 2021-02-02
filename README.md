# Service

Service é um componente da aplicação para realizar tarefas mais longas sem interface para o usuário,e a menos que seja definida, o serviço será executda na main thread e trabalham de forma concorrente.
Todo serviço precisa ser declarado no manifest
Existem três tipos de Services:

## Primeiro plano
Service em primeiro plano, realizar um operação perceptível ao usuário, Exemplo um player de musica,
o serviço presica ter a notivicação do serviço que esta em execução

## Segundo plano
Realiza ações não perceptiveis ao usuário, ultilizando a ferramente jobIntentService

## Vinculado
Um serviço vinculado permitem que outros processos, do mesmo ou de outro aplicativo sejam vinculados e interajam
como este serviço, para fornecer vinculação no serviço deve se implementar o métodom bind().

Existem três tipo de vinculação:

IBinder -> se o serviço privado e o cliente que ira vincular for da propria aplicação, Criar-se uma interface do IBinder, para que o cliente possa manipular os metodos publicos do serviço

Messege -> Caso ou aplicação queira se juntar ao serviço, cria se um Handler (Usado para comunicação entre threads) e uma interface da classe Messege, passando o IBinder pelo messege ao cliente

Este serviço não possui interface para o usuário, quando não houver mais nenhum vinculo no serviço, ele se encerra automaticamente

AIDL -> Não muito usado para serviço vinculado.

# Intent Service (DESCONTINUADA API 30 -> WorkManager JobIntentService)
Uma variação de um service, mas com a diferença que ele, por padrão já iniciar em uma nova thread para realizar suas operações.