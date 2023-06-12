package com.example.appfirestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance() // variável do banco de dados  das intâncias do firestore
        setContent {
            MaterialTheme {
                Formulario() // Chamando a função do formulário
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class) //  comandos e recursos jetpack compose material 3
    @Preview
    @Composable
    fun Formulario() {


        var nome = remember { mutableStateOf("")} // Criando a váriavel nome
        var endereco = remember {mutableStateOf("")} // Criando a váriavel endereço
        var bairro = remember {mutableStateOf("")} // Criando a váriavel bairro
        var cep = remember {mutableStateOf("")} // Criando a váriavel cep
        var cidade = remember {mutableStateOf("")} // Criando a váriavel cidade
        var estado = remember {mutableStateOf("")} // Criando a váriavel estado

        // Função remember: função do jetpack compose que permite lembrar e manter o estado de uma variável ao longo do tempo.

        Column(

            modifier = Modifier
                .fillMaxSize() // Faz com que a coluna ocupe a tela
                .padding(16.dp), // Adiciona um espaçamento interno de 16 densidades de pixels

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )



        {


            Text(
                text = "Cadastro",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 30. sp,
            )

             Spacer(modifier = Modifier.height(25.dp)) // Espaçamento entre o titulo e os inputs

            TextField(
                value = nome.value, // Define o valor do Input para a variável
                onValueChange = { nome.value = it }, // retorna o valor do campo é alterado
                label = { Text("Nome", color = MaterialTheme.colorScheme.primary) }, // Máscara para o input
                modifier = Modifier.fillMaxWidth(),

            )

            Spacer(modifier = Modifier.height(15.dp)) // Espaçamento entre os inputs


            TextField(
                value = endereco.value,
                onValueChange = { endereco.value = it },
                label = { Text("Endereço", color = MaterialTheme.colorScheme.primary)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))


            TextField(
                value = bairro.value,
                onValueChange = { bairro.value = it },
                label = { Text("Bairro", color = MaterialTheme.colorScheme.primary)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = cep.value,
                onValueChange = { cep.value = it },
                label = { Text("CEP", color = MaterialTheme.colorScheme.primary)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = cidade.value,
                onValueChange = { cidade.value = it },
                label = { Text("Cidade", color = MaterialTheme.colorScheme.primary) ) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = estado.value,
                onValueChange = { estado.value = it },
                label = { Text("Estado", color = MaterialTheme.colorScheme.primary)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    val data = hashMapOf( // Variável que armazenará todas os valores dos inputs

                        // Armazenando os valores de cada input
                        "nome" to nome.value,
                        "endereco" to endereco.value,
                        "bairro" to bairro.value,
                        "cep" to cep.value,
                        "cidade" to cidade.value,
                        "estado" to estado.value
                    )

                    firestore.collection("form") //  coleção  do cloud firestore que armazenará todos os dados
                        .add(data) // Enviando os dados armazenados na váriavel data
                        .addOnSuccessListener {
                            // Aqui foi criado automáticamente a cada cadastro um item dentro da coleção formulário com ids diferentes

                            //Limpando campos
                            nome.value = ""
                            endereco.value = ""
                            bairro.value = ""
                            cep.value = ""
                            cidade.value = ""
                            estado.value = ""
                            //
                        }
                        .addOnFailureListener { e -> // Se caso não der certo ao enviar dados
                        }
                },
                modifier = Modifier.padding(bottom = 100.dp), // Define um espaçamento na parte inferior do layout
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,

                )
            )

            {
                Text(
                    text = "Cadastrar", fontSize = 22. sp,)
            }
        }
    }
}


