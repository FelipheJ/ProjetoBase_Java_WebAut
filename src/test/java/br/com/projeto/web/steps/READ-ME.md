#Steps

<p>Os steps são classes para abstração dos passo-a-passos citados no BDD.</p>
<p>Cada método contém uma anotação <i>(Dado, E, Quando, Então, Mas)</i> que serve para caracterizar qual tio de step o método é.</p>
<br>Exemplo:<br>@Dado("Que acesso o site {string}")</br><br>public void acessarSite(String url);</br></p>