package Bahrom;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Handler {
    private final Bot bot;
     
    public Handler(Bot bot) {
        this.bot = bot;
    }

    public void handle(Update update) throws Exception {
        if (!update.hasMessage()) return;
        Long chatId = update.getMessage().getChatId();
        String name = update.getMessage().getFrom().getFirstName();


        if (update.getMessage().hasContact()) {
            handleBookingFinal(update, chatId, name);
            return;
        }

        if (!update.getMessage().hasText()) return;
        String text = update.getMessage().getText().trim();
        String lowerText = text.toLowerCase();


        if (text.startsWith("/start")) {
            handleStart(chatId, text, name);
            return;
        }


        if (lowerText.contains("konkurs") || lowerText.contains("sovrin") || text.contains("🎁")) {
            handleKonkurs(chatId);
            return;
        }


        if (lowerText.contains("xonalar") || lowerText.contains("eksklyuziv")) {
            handleRooms(chatId);
            return;
        }


        if (lowerText.contains("standard")) {
            handleStandard(chatId);
            return;
        }


        if (lowerText.contains("business")) {
            handleBusiness(chatId);
            return;
        }


        if (lowerText.contains("royal") || lowerText.contains("family")) {
            handleRoyal(chatId);
            return;
        }


        if (lowerText.contains("premium") || lowerText.contains("xizmatlar")) {
            handlePremium(chatId);
            return;
        }


        if (lowerText.contains("manzil")) {
            handleLocation(chatId);
            return;
        }


        if (lowerText.contains("aloqa") || lowerText.contains("markazi")) {
            handleContact(chatId);
            return;
        }


        if (lowerText.contains("haqida") || lowerText.contains("hotel")) {
            handleAbout(chatId);
            return;
        }

        sendWelcome(chatId);
    }

    private void handleStart(Long chatId, String text, String name) throws Exception {
        if (text.contains(" ")) {
            String refIdStr = text.split(" ")[1];
            try {
                Long refId = Long.parseLong(refIdStr);
                if (!refId.equals(chatId) && !UserState.userPoints.containsKey(chatId)) {
                    int p = UserState.userPoints.getOrDefault(refId, 0) + 10;
                    UserState.userPoints.put(refId, p);
                    bot.execute(new SendMessage(refId.toString(), "🎉 **DO'STINGIZDAN SOVG'A!** \nSizning linklingiz orqali kimdir qo'shildi: **+10 BALL**! 🎁"));
                }
            } catch (Exception e) {}
        }
        if (!UserState.userPoints.containsKey(chatId)) UserState.userPoints.put(chatId, 0);

        String startMsg = "👑 **GRAND IMPERIAL HOTEL BOTIGA XUSH KELIBSIZ!** 👑\n" +
                "──────────────────────────\n" +
                "Assalomu alaykum, muhtaram **" + name + "**! 🌟\n\n" +
                "Bizning shinam mehmonxonamizga xush kelibsiz. Bu yerda siz:\n" +
                "🏨 Xonalarni bron qilishingiz;\n" +
                "🎁 Konkurslarda qatnashib, ball to'plashingiz;\n" +
                "🏊‍♂️ VIP xizmatlar bilan tanishishingiz mumkin!\n\n" +
                "👇 **Menyudan bo'limni tanlang:**";
        sendPhoto(chatId, "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=1000", startMsg, Keyboard.mainMenu());
    }

    private void handleKonkurs(Long chatId) throws Exception {
        int points = UserState.userPoints.getOrDefault(chatId, 0);
        String link = "https://t.me/baxa_sambot?start=" + chatId;

        String bar = (points >= 500) ? "██████████ 100%" : (points >= 150) ? "██████░░░░ 60%" : (points >= 50) ? "███░░░░░░░ 30%" : "░░░░░░░░░░ 0%";

        String msg = "🎁 **GRAND IMPERIAL: SOVRINLAR VA KONKURS** 🎁\n" +
                "──────────────────────────\n" +
                "📊 **SIZNING NATIJANGIZ:** 👉 **" + points + " BALL** 👈\n" +
                "📈 Progress: `[" + bar + "]` \n" +
                "──────────────────────────\n\n" +
                "💎 **BALL TO'PLASH QOIDALARI:**\n" +
                "👥 Do'stingizni taklif qilsangiz: **+10 BALL**\n" +
                "👑 Royal Suite band qilsangiz: **+100 BALL**\n\n" +
                "🏆 **SOVRINLARIMIZ:**\n" +
                "🥉 **50 Ball:** VIP Basseyn va Fitness 🏊‍♂️\n" +
                "🥈 **150 Ball:** Restoranda kechki ovqat 🍽\n" +
                "🥇 **500 Ball:** Business Luxury xonasida BEPUL tunash! 💎\n\n" +
                "👇 **SIZNING SHAXSIY LINKINGIZ:**\n" +
                "(Nusxalash uchun ustiga bosing)\n\n" +
                "`" + link + "`\n\n" +
                "📣 *Ushbu linkni do'stlaringizga tarqating!* 🏁";
        sendPhoto(chatId, "https://images.unsplash.com/photo-1523217582562-09d0def993a6?w=1000", msg, Keyboard.mainMenu());
    }

    private void handleRooms(Long chatId) throws Exception {
        String msg = "🏨 **GRAND IMPERIAL — EKSKLYUZIV XONALAR** 🏨\n" +
                "Bizda har bir xona o'zgacha shinamlikka ega! ✨\n\n" +
                "✅ Panorama ko'rinish, yuqori tezlikdagi Wi-Fi, 24/7 xizmat! 🤵‍♂️\n\n" +
                "👇 **Toifani tanlang:**";
        sendPhoto(chatId, "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?w=1000", msg, Keyboard.roomMenu());
    }

    private void handleStandard(Long chatId) throws Exception {
        String msg = "🟤 **STANDARD CLASS — SHINAM MUHIT** 🛋\n" +
                "🔹 **Xususiyatlari:** 30 kv.m maydon, King-size yotoq, Smart TV. ☕️\n" +
                "💰 **Narxi:** $100 / sutka\n" +
                "✍️ **Bron qilish uchun kontaktingizni yuboring:**";
        sendPhoto(chatId, "https://images.unsplash.com/photo-1611892440504-42a792e24d32?w=1000", msg, Keyboard.phoneRequest());
        UserState.waitingForPhone.put(chatId, "Standard Class");
    }

    private void handleBusiness(Long chatId) throws Exception {
        String msg = "💎 **BUSINESS LUXURY — PREMIUM STATUS** 🏆\n" +
                "🔸 **Xususiyatlari:** Ishchi zona, Panorama va SPA markaziga bepul kirish! 🧖‍♂️\n" +
                "💰 **Narxi:** $250 / sutka\n" +
                "✍️ **Bron qilish uchun kontaktingizni yuboring:**";
        sendPhoto(chatId, "https://images.unsplash.com/photo-1590490360182-c33d57733427?w=1000", msg, Keyboard.phoneRequest());
        UserState.waitingForPhone.put(chatId, "Business Luxury");
    }

    private void handleRoyal(Long chatId) throws Exception {
        String msg = "👑 **ROYAL FAMILY SUITE — QIROLLIK HASHAMATI** 🏰\n" +
                "🔥 **MAXSUS TAKLIF: +100 BALL!** 🎁🚀\n" +
                "⚜️ **Xususiyatlari:** 3 ta xona, shaxsiy jakuzi va sauna! 🤵‍♂️🛁\n" +
                "💰 **Narxi:** $550 / sutka\n\n" +
                "✍️ **Bron qilish uchun kontaktingizni yuboring:**";
        sendPhoto(chatId, "https://images.unsplash.com/photo-1582719478250-c89cae4dc85b?w=1000", msg, Keyboard.phoneRequest());
        UserState.waitingForPhone.put(chatId, "Royal Family Suite");
    }

    private void handlePremium(Long chatId) throws Exception {
        String msg = "✨ **GRAND IMPERIAL — PREMIUM XIZMATLAR** ✨\n" +
                "💧 **SPA:** Turk hamomi va massaj. 🧖‍♀️\n" +
                "🏊‍♂️ **AQUA:** Isitiladigan panorama basseyn. 🌊\n" +
                "🍽 **RESTORAN:** Dunyo oshpazlaridan shohona taomlar! 🎻🥩";
        sendPhoto(chatId, "https://images.unsplash.com/photo-1540555700478-4be289fbecef?w=1000", msg, Keyboard.mainMenu());
    }

    private void handleLocation(Long chatId) throws Exception {
        bot.execute(new SendLocation(chatId.toString(), 41.3385, 69.2850));
        SendMessage m = new SendMessage(chatId.toString(), "📍 **MANZIL:** Toshkent sh., Amir Temur 108. (Teleminora ro'parasi)");
        m.setReplyMarkup(Keyboard.mainMenu());
        bot.execute(m);
    }

    private void handleContact(Long chatId) throws Exception {
        SendMessage m = new SendMessage(chatId.toString(), "📞 **ALOQA (24/7):**\n☎️ +998 71 200 00 00\n👨‍💻 Admin: @Imperial_Hotel_Admin");
        m.setReplyMarkup(Keyboard.mainMenu());
        bot.execute(m);
    }

    private void handleAbout(Long chatId) throws Exception {
        sendPhoto(chatId, "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?w=1000", "🏆 **GRAND IMPERIAL HOTEL** — 2014-yildan beri sizning xizmatingizda! 🤝✨", Keyboard.mainMenu());
    }

    private void handleBookingFinal(Update update, Long chatId, String name) throws Exception {
        String phone = update.getMessage().getContact().getPhoneNumber();
        String room = UserState.waitingForPhone.getOrDefault(chatId, "Noma'lum");
        String finalMsg = "✅ **QABUL QILINDI!** \nTez orada bog'lanamiz.";

        if (room.contains("Royal Family")) {
            int p = UserState.userPoints.getOrDefault(chatId, 0) + 100;
            UserState.userPoints.put(chatId, p);
            finalMsg += "\n🎁 **BONUS: +100 BALL!** 👑";
        }

        bot.execute(new SendMessage(Config.CHANNEL_ID, "🛎 **BRON:** " + name + " (" + phone + ") | Xona: " + room));
        SendMessage s = new SendMessage(chatId.toString(), finalMsg);
        s.setReplyMarkup(Keyboard.mainMenu());
        bot.execute(s);
        UserState.waitingForPhone.remove(chatId);
    }

    private void sendWelcome(Long chatId) throws Exception {
        SendMessage m = new SendMessage(chatId.toString(), "🏠 **ASOSIY MENYU**");
        m.setReplyMarkup(Keyboard.mainMenu());
        bot.execute(m);
    }

    private void sendPhoto(Long chatId, String url, String caption, org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup kb) throws Exception {
        SendPhoto p = new SendPhoto(chatId.toString(), new InputFile(url));
        p.setCaption(caption); p.setParseMode("Markdown"); p.setReplyMarkup(kb);
        bot.execute(p);
    }
}