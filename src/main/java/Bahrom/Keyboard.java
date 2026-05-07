package Bahrom;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import java.util.List;

public class Keyboard {
    public static ReplyKeyboardMarkup mainMenu() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add("🏨 Eksklyuziv Xonalar");
        row1.add("✨ Premium Xizmatlar");
        KeyboardRow row2 = new KeyboardRow();
        row2.add("🎁 Konkurs & Sovrinlar");
        row2.add("📍 Bizning Manzil");
        KeyboardRow row3 = new KeyboardRow();
        row3.add("📞 Aloqa Markazi");
        row3.add("ℹ️ Hotel Haqida");
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setKeyboard(List.of(row1, row2, row3));
        keyboard.setResizeKeyboard(true);
        return keyboard;
    }

    public static ReplyKeyboardMarkup roomMenu() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add("🟤 Standard Class");
        row1.add("💎 Business Luxury");
        KeyboardRow row2 = new KeyboardRow();
        row2.add("👑 Royal Family Suite");
        row2.add("🔙 Asosiy Menyu");
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setKeyboard(List.of(row1, row2));
        keyboard.setResizeKeyboard(true);
        return keyboard;
    }

    public static ReplyKeyboardMarkup phoneRequest() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton btn = new KeyboardButton("📱 Kontakni ulashish");
        btn.setRequestContact(true);
        row1.add(btn);
        KeyboardRow row2 = new KeyboardRow();
        row2.add("❌ Bekor qilish");
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setKeyboard(List.of(row1, row2));
        keyboard.setResizeKeyboard(true);
        return keyboard;
    }
}