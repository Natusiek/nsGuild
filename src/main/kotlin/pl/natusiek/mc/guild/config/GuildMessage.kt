package pl.natusiek.mc.guild.config

class GuildMessage {

    val unknownCharacters = "&4Blad: &cW podnaych wartościach pojawiły się nieznane znaki :("
    val uAreInGuildRegion = "&4Jesteś na terenie innej gildii nie możesz tego robić :("
    val uHaveGuild = "&4Przykro nam albo i nie, ale już posiadasz gildiee :}"
    val uNotHaveGuild = "&4Blad: &cNie posiadasz gildi."
    val uNotLeader = "&4Blad: &cNie jestes liderem."
    val targetIsOffline = "&4Blad: &cGracz nie jest online."
    val targetHaveGuild = "&4Blad: &cPodany gracz posiada juz gildie."


    val create = Create()
    class Create {
        val guildIsNotEnable = "&4Blad: &cZakładanie gildii nie są jeszcze włączone."
        val minLengthTag = "&4Blad: &cMinimalna długośc tagu to: &f%lenght%."
        val maxLengthTag = "&4Blad: &cMaksymalna długośc tagu to: &f%lenght%."
        val maxLengthName = "&4Blad: &cMaksymalna długośc nazwy to: &f%lenght%."
        val tagIsBusy = "&4Blad: &cPodany tag już jest zajęty."
        val nameIsBusy = "&4Blad: &cPodana nazwa już jest zajęta."
        val noHaveItemToCreate = "&4Blad: &cNie posiadasz itemow do założenia sprawdz pod: &f./g itemy &cczego ci brakuje."
        val uAreInSpawn = "&4Blad: &cNa terenie spawna nie możesz zakładać gildii."
        val tooCloseGuild = "&4Blad: &cZnajdujesz sie zbyt blisko gildii."
        val completeCreateGuild = arrayOf("", " &8* &eZostała założona gilda: &f%guild% przez: &f%leader%", "")
    }

    val invite = Invite()
    class Invite {
        val senderMessage = arrayOf("", "&eWysłałeś graczowi: &f%target%, &ezaproszenie do gildii.", "")
        val targetMessage = arrayOf("", " &8* &eOtrzymales zaproszenie do gildi: &f%guild% &eprzez &f%sender%", "    &8>> &fAby dołączyć do gildii wpisz: &e/g dolacz %guild%", "")
    }

}