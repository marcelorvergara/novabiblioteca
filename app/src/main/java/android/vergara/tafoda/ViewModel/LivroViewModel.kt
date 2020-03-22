package android.vergara.tafoda.ViewModel

import android.util.Log
import android.vergara.tafoda.Model.Note
import androidx.lifecycle.ViewModel

class LivroViewModel: ViewModel() {
    lateinit var livro: List<Note>
    lateinit var um_livro : Note

    fun notes(): List<Note> {

        var theBooks : MutableList<Note> = mutableListOf(
            Note(
                "Livro de Kotlin com Android",
                "Este livro ensina a desenvolver aplicativos Android com a linguagem Kotlin.",
                "Autor: Nelson Glauber",
                "Resumo: O Android é a plataforma do Google para dispositivos móveis que se tornou líder absoluta no mercado mundial, e a quantidade de recursos disponibilizada para os desenvolvedores permite criar uma envolvente e estimulante interação do usuário com o dispositivo.",
                "550",
                "1"
            ),
            Note(
                "Gestão de produtos de software",
                "Como aumentar as chances de sucesso do seu software",
                "Autor: Joaquim Torres",
                "Resumo: Cuidar do andamento de um projeto de software é uma tarefa que requer diversos conhecimentos. Saber lidar com pressão de clientes, anseios do time de desenvolvimento, necessidades da equipe de vendas. Como negociar prazos? O que deve ser desenvolvido primeiro no roadmap do meu produto?",
                "210",
                "2"
            ),
            Note(
                "Como sincronizar minha App com um Web Service",
                "Web Services REST com ASP .NET Web API e Windows Azure",
                "Autor: Paulo Siécola",
                "Resumo: A utilização de serviços REST é uma tendência que vem crescendo muito nos últimos anos, principalmente em APIs públicas, e ASP.NET Web API é a plataforma ideal para a criação de aplicações RESTful sob a plataforma .NET da Microsoft.",
                "320",
                "3"
            ),
            Note(
                "Dom Quixote, 1605",
                "Considerada a maior obra da literatura espanhola e o segundo livro mais lido da História",
                "Autor: Miguel de Cervantes",
                "Resumo: Uma obra do escritor espanhol Miguel de Cervantes, publicada em duas partes. A primeira surgiu em 1605 e a segunda dez anos depois, em 1615. Quando o livro foi traduzido para inglês e francês obteve um êxito súbito, arrebatando leitores de diversas origens.",
                "220",
                "4"
            ),
            Note(
                "Guerra e Paz, 1869",
                "É um romance histórico escrito pelo autor russo Liev Tolstói e publicado entre 1865 e 1869 no Russkii Vestnik, um periódico da época.",
                "Autor: Liev Tolstói",
                "Resumo: Guerra e Paz é um romance histórico escrito pelo autor russo Liev Tolstói e publicado entre 1865 e 1869 no Russkii Vestnik, um periódico da época. É uma das obras mais volumosas da história da literatura universal. O livro narra a história da Rússia à época de Napoleão Bonaparte.",
                "679",
                "5"
            ),
            Note(
                "Rede de Computadores - Topologias Avançadas",
                "Distributed Systems: Principles and Paradigms",
                "Autor: Andrew S. Tanenbaum",
                "Resumo: Esse novo livro, em co-autoria com Maarten van Steen, aborda tanto os conceitos fundamentais quanto os paradigmas dos modernos sistemas distribuídos.",
                "750",
                "6"
            ),
            Note(
                "Os lusíadas",
                "Esta obra é considerada a epopéia portuguesa por excelência",
                "Autor: de Luís de Camões",
                "Resumo: O livro foi publicado pela primeira vez em 1572 no período literário do Classicismo, três anos após o regresso do autor do Oriente. Os lusíadas é composto de dez cantos, somando 1.102 estrofes, todas oitavas de decassílabos heróicos, obedecendo ao esquema rimático “abababcc” (rimas cruzadas, nos seis primeiros versos, e emparelhada, nos dois últimos). A ação central do poema é a descoberta do caminho marítimo para a Índia por Vasco da Gama, a volta da qual se vão descrevendo outros episódios da história de Portugal, glorificando o povo português.",
                "433",
                "7"
            ),
            Note(
                "As flores do mal",
                "O poeta e crítico e francês Charles Baudelaire marcou as últimas da década de 19, influenciando a poesia internacional de tendência simbolista. De sua maneira de ser, originaram-se na França os poetas “malditos”.",
                "Autor: Charles Baudelaire",
                "Resumo:  Baudelaire inventou uma nova estratégia de linguagem, incorporando a matéria da realidade grotesca à linguagem sublimada do Romantismo, dando base para a criação da poesia moderna.",
                "98",
                "8"
            ),
            Note(
                "Vigiar e punir",
                "A lista também não poderia deixar de lado um dos principais filósofos do mundo",
                "Autor: Foucault",
                "Resumo: Michel Foucault (1926-1984). Vigiar e punir é um estudo científico, documentado, sobre a evolução histórica da legislação penal e respectivos métodos coercitivos e punitivos, adotados pelo poder público na repressão da delinquência. Métodos que vão desde a violência física até instituições correcionais.",
                "50",
                "9"
            ),
            Note(
                "Cem anos de solidão",
                "Neste livro, Gabriel García Márquez narra a história da família Buendía",
                "Autor: Gabriel García Márquez",
                "Resumo: Uma estirpe de solitários que habitam a mítica aldeia de Macondo. A narrativa se desenvolve em torno dos integrantes dessa família, com a particularidade de que todas as gerações foram acompanhadas por Úrsula, uma personagem centenária e matriarca das mais conhecidas da história da literatura latino-americana.",
                "320",
                "10"
            ),
            Note(
                "Moby Dick",
                "O nome deste livro é o do cachalote (um animal parecido com uma baleia) enfurecido",
                "Autor: Herman Melville",
                "Resumo: Publicado inicialmente em 1851, Moby Dick foi revolucionário para a época, com descrições intrincadas e imaginativas das aventuras do narrador Ismael. O romance foi baseado no naufrágio do navio Essex, comandado pelo capitão George Pollard. Na ocasião, ele foi atingido por uma baleia e afundou.",
                "342",
                "11"
            ),
            Note(
                "Sobre os Ossos dos Mortos",
                "Essa senhora polonesa recebeu o prêmio Nobel em 2018 e, se você ainda não a conhece, corra agora para a conhecer.",
                "Autor: Olga Tokarczuk",
                "Resumo: Janina mora em um vilarejo afastado com dois cães. Seu drama se inicia quando os cachorros desaparecem - e, para completar o mistério, na pacata região duas pessoas aparecem mortas também sem motivo. Esse enredo cheio de questões serve de pretexto para uma autora madura refletir sobre as questões filosóficas da vida.",
                "502",
                "12"
            ),
            Note(
                "O ano do pensamento mágico",
                "O luto é dos temas mais duros que um escritor pode abordar. Joan Didion enfrenta, no entanto, com uma coragem ímpar, o desafio proposto.",
                "Autor: Joan Didion",
                "Resumo:O ano do pensamento mágico é uma obra autobiográfica que foi escrita a partir da experiência da autora de ter perdido o marido de modo repentino. Antes de ser um livro pesado ou deprimente, a abordagem escolhida pela escritora é a da honestidade absoluta e a da transmissão de dados recolhidos a partir de investigações científicas. Sim, Didion foi a procura de uma série de estudos sobre o luto para ajudá-la a compreender melhor o que estava se passando no seu próprio corpo. Generosamente esses ensinamentos foram partilhados com o leitor, que encerra a leitura do livro muito mais rico e consciente da finitude da vida",
                "502",
                "13"
            ),
            Note(
                "Don Juan (Narrado por Ele Mesmo)",
                "O vencedor do último prêmio Nobel de Literatura é Peter Handke, um autor e dramaturgo austríaco que merece mesmo ser conhecido.",
                "Autor: Peter Handke",
                "Resumo: Uma das suas obras mais celebradas é Don Juan (Narrado por Ele Mesmo), que conta a história de um homem solitário que faz da leitura o seu principal hobby. Cozinheiro de profissão, sempre que pode mergulha nas páginas dos livros. De tanto gostar de ler, um dia, de súbito, o icônico personagem da literatura Don Juan aparece no seu quintal dando início a um diálogo interessante, curioso e original.",
                "502",
                "14"
            ),
            Note(
                "A revolução dos bichos",
                "Em tempos politicamente cada vez mais conturbados, vale ler novamente A revolução dos bichos, um clássico escrito por George Orwell.",
                "Autor: George Orwell",
                "Resumo: A fábula que foi publicada em 1945 permanece atual até os dias de hoje e a sua leitura nos faz embarcar em um mundo paralelo que, ao contrário do que possa parecer a primeira vista, tem muito a ver com o nosso. A história se passa na Granja do Solar, onde uma série de bichos se reúne para fazer uma revolução. São galinhas, pombas, cachorros, cavalos, vacas, cabras, burros e ovelhas que possuem características humanas.",
                "502",
                "15"
            ),
            Note(
                "Os da minha rua",
                "Ondjaki é o pseudônimo do escritor Ndalu de Almeida, um dos maiores nomes da literatura contemporânea angolana.",
                "Autor: Ondjaki",
                "Resumo: Com uma forte pegada autobiográfica, o livro consegue ser ao mesmo tempo pessoal e universal porque fala de uma infância específica embora nós, leitores, também sintamos essas breves histórias ecoarem dentro de nós, falando das nossas próprias experiências singulares.",
                "502",
                "16"
            ),
            Note(
                "A Arte Subtil de Saber Dizer que se F*da",
                "Publicado no princípio de 2018, o livro de Mark Manson é duro, cru, mas ao mesmo tempo extremamente honesto e tece uma crítica à sociedade contemporânea que se habituou ao pensamento positivo.",
                "Autor: de Mark Manson",
                "Resumo: O leitor que aceitar o desafio proposto pela leitura certamente sairá com um olhar \"fora da caixinha\" porque o que Mark Manson faz é desconstruir lugares comuns. Um exemplo pode ser encontrado no capítulo 3, intitulado \"Você não é especial\". Ao contrário do que a sociedade tenta incutir, somos confrontados com a dura realidade de que somos mais um no meio da multidão, sofrendo de inseguranças e medos similares. A escrita de Manson é anti o sonho americano, anti as histórias perfeitas, de superação, anti a felicidade plena idealizada. Trata-se de um elogio ao ser humano comum, médio, com os seus altos e baixos. A Arte Subtil de Saber Dizer que se F*da é uma tentativa de deixar o sujeito confortável com a sua própria condição e com os seus fracassos.",
                "502",
                "17"
            )
        )

    return theBooks
    }
}