package pl.natusiek.mc.guild.config

class GuildMessage {

    val unknownCharacters = "&4Blad: &cW podnaych wartościach pojawiły się nieznane znaki :("
    val uHaveGuild = "&4Przykro nam albo i nie, ale już posiadasz gildiee :}"

    val create = Create()
    class Create {
        val minLengthTag = "&4Blad: &cMinimalna długośc tagu to: &f%lenght%."
        val maxLengthTag = "&4Blad: &cMaksymalna długośc tagu to: &f%lenght%."
        val maxLengthName = "&4Blad: &cMaksymalna długośc nazwy to: &f%lenght%."
        val tagIsBusy = "&4Blad: &cPodany tag już jest zajęty."
        val nameIsBusy = "&4Blad: &cPodana nazwa już jest zajęta."
        val noHaveItemToCreate = "&4Blad: &cNie posiadasz itemow do założenia sprawdz pod: &f./g itemy &cczego ci brakuje."
    }

}