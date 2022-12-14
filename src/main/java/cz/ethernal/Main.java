package cz.ethernal;

import cz.ethernal.Commands.Fun.CoinFlip;
import cz.ethernal.Commands.Moderation.*;
import cz.ethernal.Commands.Others.DirectMsg;
import cz.ethernal.Commands.Others.Ip;
import cz.ethernal.Commands.Others.CekarnaInfo;
import cz.ethernal.JoinSystem.WelcomeMessage;
import cz.ethernal.Moderation.AutoMod;
import cz.ethernal.MusicSystem.Commands.*;
import cz.ethernal.SuggestSystem.SuggestCmd;
import cz.ethernal.TicketSystem.Tickets;
import cz.ethernal.VCSystem.Commands.*;
import cz.ethernal.VCSystem.VCHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {

    private static JDA bot;
    public static void main(String[] args) throws LoginException, InterruptedException {

        bot = JDABuilder.createDefault(Config.token)
                .addEventListeners(new Ban())
                .setActivity(Activity.playing("play.ethernal.cz"))
                .addEventListeners(new Slowmode())
                .addEventListeners(new Kick())
                .addEventListeners(new Clear())
                .addEventListeners(new DirectMsg())
                .addEventListeners(new CoinFlip())
                .addEventListeners(new VCHandler())
                .addEventListeners(new Say())
                .addEventListeners(new AutoMod())
                .addEventListeners(new CekarnaInfo())
                .addEventListeners(new Tickets())
                //.addEventListeners(new SuggestCmd())
                .addEventListeners(new Help())
                .addEventListeners(new Rename())
                .addEventListeners(new Ip())
                .addEventListeners(new Lock())
                .addEventListeners(new Add())
                .addEventListeners(new Unlock())
                .addEventListeners(new Remove())
                .addEventListeners(new Limit())
                .addEventListeners(new Show())
                .addEventListeners(new Hide())
                .addEventListeners(new Play())
                .addEventListeners(new Stop())
                .addEventListeners(new Skip())
                .addEventListeners(new NowPlaying())
                .addEventListeners(new Queue())
                .addEventListeners(new Loop())
                .addEventListeners(new Resume())
                .addEventListeners(new Pause())
                .addEventListeners(new Volume())
                .addEventListeners(new Private())
                .addEventListeners(new ClearQueue())
                .addEventListeners(new WelcomeMessage())
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableCache(CacheFlag.VOICE_STATE)
                .build().awaitReady();

        Guild server = bot.getGuildById(Config.guild_id);
        if (server != null) {

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("kick", "Vyhod???? hr????e ze serveru")
                    .addOption(OptionType.MENTIONABLE, "hr????", "Jm??no hr????e, kter??ho chce?? vyhodit", true)
                    .addOption(OptionType.STRING, "d??vod", "D??vod, pro?? byl hr???? vyhozen", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("slowmode", "Nastav???? cooldown psan?? v dan??m kan??lu")
                    .addOption(OptionType.INTEGER, "sekundy", "????slo v sekund??ch", true)
                    .addOption(OptionType.CHANNEL, "kan??l", "Ozna?? kan??l, kter?? chce?? upravit", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("cooldown", "Nastav???? cooldown psan?? v dan??m kan??lu")
                    .addOption(OptionType.INTEGER, "sekundy", "????slo v sekund??ch", true)
                    .addOption(OptionType.CHANNEL, "kan??l", "Ozna?? kan??l, kter?? chce?? upravit", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("ban", "Zabanuje?? hr????e na serveru")
                    .addOption(OptionType.MENTIONABLE, "hr????", "Jm??no hr????e, kter??ho chce?? vyhodit", true)
                    .addOption(OptionType.STRING, "d??vod", "D??vod, pro?? byl hr???? vyhozen", true)
                    .addOption(OptionType.STRING, "??as", "Na kolik dn?? chce?? hr????e zabanovat, napi?? 0 pro permanentn??", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("clear", "Sma??e?? dan?? po??et zpr??v v textov??m kan??le")
                    .addOption(OptionType.INTEGER, "po??et", "Po??et zpr??v, kter?? chce?? smazat", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("dm", "Po??le?? hr????i, skrz bota, zpr??vu do soukrom??ch zpr??v")
                    .addOption(OptionType.MENTIONABLE, "hr????", "Hr????, kter??mu chce?? zpr??vu poslat", true)
                    .addOption(OptionType.STRING, "zpr??va", "Zpr??va, kterou chce?? hr????i poslat", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("pm", "Po??le?? hr????i, skrz bota, zpr??vu do soukrom??ch zpr??v")
                    .addOption(OptionType.MENTIONABLE, "hr????", "Hr????, kter??mu chce?? zpr??vu poslat", true)
                    .addOption(OptionType.STRING, "zpr??va", "Zpr??va, kterou chce?? hr????i poslat", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("coinflip", "Hod???? minc??! Panna nebo Orel!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("say", "Napi?? zpr??vu, kterou chce?? aby bot napsal")
                    .addOption(OptionType.STRING, "zpr??va", "Zpr??va, kterou chce?? poslat", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("tsetup", "Po??le?? z??kladn?? zpr??vu pro vytvo??en?? ticket??")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            //server.upsertCommand("suggest", "Navrhni sv??j n??pad a nech ostatn?? hr????e hlasovat o tv??m n??padu!")
            //        .addOption(OptionType.STRING, "zpr??va", "Tv??j n??pad", true)
            //        .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("n??pad", "Navrhni sv??j n??pad a nech ostatn?? hr????e hlasovat o tv??m n??padu!")
                    .addOption(OptionType.STRING, "zpr??va", "Tv??j n??pad", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("ip", "Zjisti IP na??eho server!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice", "Vypsan?? ve??ker?? informace pro ovl??d??n?? hlasov??ch kan??l??!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("tinfo", "Vypsan?? ve??ker?? informace ohledn?? ??ek??rny!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice-lock", "Uzamkne?? sv??j do??asn?? hlasov?? kan??l!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice-unlock", "Odemkne?? sv??j do??asn?? hlasov?? kan??l!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice-add", "P??id???? hr????ovi p????stup do tv??ho do??asn??ho kan??lu!!")
                    .addOption(OptionType.MENTIONABLE, "hr????", "Hr????e kter??ho chce?? p??idat", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice-remove", "Odebere?? hr????ovi p????stup z tv??ho do??asn??ho kan??lu!!")
                    .addOption(OptionType.MENTIONABLE, "hr????", "Hr????e kter??ho chce?? odebrat", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice-rename", "P??ejmenuje?? sv??j do??asn?? hlasov?? kan??l!")
                    .addOption(OptionType.STRING, "n??zev", "Nov?? jm??no kan??lu", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice-limit", "Nastav???? maxim??ln?? po??et u??ivatel?? v tv??m do??asn??m kan??le!")
                    .addOption(OptionType.INTEGER, "po??et", "Maxim??ln?? po??et hr??????", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice-show", "Zobraz???? v??em hr??????m sv??j do??asn?? kan??l!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("voice-hide", "Skryje?? v??em hr??????m sv??j do??asn?? kan??l!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("play", "Spust???? p??sni??ku!")
                    .addOption(OptionType.STRING, "n??zev", "N??zev p??sni??ky ??i playlistu, kter?? chce?? pustit!", true)
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("stop", "Vyhod???? bota a sma??e?? jeho queue!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("skip", "P??esko?????? p??sni??ku!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("np", "Zobraz???? informace o aktu??ln?? p??sni??ce!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("nowplaying", "Zobraz???? informace o aktu??ln?? p??sni??ce!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("queue", "Zobraz???? informace o aktu??ln?? front??!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("loop", "Nastav???? re??im opakov??n?? p??sni??ky!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("pause", "Pozastav???? p??sni??ku v botovi!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("resume", "Nech???? pokra??ovat aktu??ln?? p??sni??ku v botovi!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            //server.upsertCommand("volume", "Nastav???? hlasitost bota!")
                    //.addOption(OptionType.INTEGER, "hlasitost", "????slo od 0-100", true)
                    //.queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("cq", "Vyma??e?? botovi queue!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            server.upsertCommand("clearqueue", "Vyma??e?? botovi queue!")
                    .queue();

            //------------------------------------------------------------------------------------------------------------------------------------

            //server.upsertCommand("private", "Po??le?? ka??d??mu ??lenovi na serveru zpr??vu!")
            //        .queue();

            //------------------------------------------------------------------------------------------------------------------------------------


        }else {
            System.out.println("Server nenalezen!");
        }

    }
}
