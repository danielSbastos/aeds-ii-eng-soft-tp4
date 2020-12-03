Importe o arquivo `settings.zip` em "File -> Manage IDE Settings -> Import settings"

Essa configuração direciona o `stdin` para o `pub.in` de cada módulo e direciona o stdout para `pub.out.2`. Para checar se a sua saída é igual ao `pub.out`, basta ir no terminal e na pasta do modulo e escrever `diff pub.out pub.out.2`