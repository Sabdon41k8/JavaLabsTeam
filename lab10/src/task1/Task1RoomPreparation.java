package lab10.src.task1;

import java.util.List;
 
public class Task1RoomPreparation {
 
    // AbstractProduct (сімейство продуктів)
 
    /** Спільна абстракція будь-якого елемента комплекту номера. */
    interface RoomItem {
        String category();
        String describe();
    }
 
    interface CosmeticsSet extends RoomItem {}   
    interface MinibarContent extends RoomItem {} 
    interface Textile extends RoomItem {}        
 
    /** Допоміжна база, щоб не дублювати поля в конкретних продуктах. */
    abstract static class BaseItem implements RoomItem {
        private final String category;
        private final String title;
        private final String content;
 
        BaseItem(String category, String title, String content) {
            this.category = category;
            this.title = title;
            this.content = content;
        }
 
        @Override public String category() { return category; }
        @Override public String describe() { return title + " [" + category + "]: " + content; }
    }
 
    // ConcreteProduct: стандарт
 
    static class StandardCosmetics extends BaseItem implements CosmeticsSet {
        StandardCosmetics() { super("стандарт", "Косметика", "шампунь, мило"); }
    }
    static class StandardMinibar extends BaseItem implements MinibarContent {
        StandardMinibar() { super("стандарт", "Мінібар", "вода, сік (за тарифом «стандарт»)"); }
    }
    static class StandardTextile extends BaseItem implements Textile {
        StandardTextile() { super("стандарт", "Текстиль", "2 рушники, 1 комплект постільної білизни"); }
    }
 
    // ConcreteProduct: люкс 
 
    static class LuxeCosmetics extends BaseItem implements CosmeticsSet {
        LuxeCosmetics() { super("люкс", "Косметика", "шампунь, кондиціонер, гель, лосьйон, халат"); }
    }
    static class LuxeMinibar extends BaseItem implements MinibarContent {
        LuxeMinibar() { super("люкс", "Мінібар", "шампанське, вино, вода, снеки (за тарифом «люкс»)"); }
    }
    static class LuxeTextile extends BaseItem implements Textile {
        LuxeTextile() { super("люкс", "Текстиль", "4 рушники, халати, постіль із єгипетської бавовни"); }
    }
 
    //  ConcreteProduct: сімейний 
 
    static class FamilyCosmetics extends BaseItem implements CosmeticsSet {
        FamilyCosmetics() { super("сімейний", "Косметика", "дитячий шампунь, мило, мочалки"); }
    }
    static class FamilyMinibar extends BaseItem implements MinibarContent {
        FamilyMinibar() { super("сімейний", "Мінібар", "соки, печиво, вода (за тарифом «сімейний»)"); }
    }
    static class FamilyTextile extends BaseItem implements Textile {
        FamilyTextile() { super("сімейний", "Текстиль", "6 рушників, дві постелі, дитячий плед"); }
    }
 
    // AbstractFactory
 
    /** Видає лише узгоджений комплект ОДНІЄЇ категорії. */
    interface RoomKitFactory {
        String category();
        CosmeticsSet createCosmetics();
        MinibarContent createMinibar();
        Textile createTextile();
    }
 
    // ConcreteFactory 
 
    static class StandardRoomKitFactory implements RoomKitFactory {
        @Override public String category() { return "стандарт"; }
        @Override public CosmeticsSet createCosmetics() { return new StandardCosmetics(); }
        @Override public MinibarContent createMinibar() { return new StandardMinibar(); }
        @Override public Textile createTextile() { return new StandardTextile(); }
    }
 
    static class LuxeRoomKitFactory implements RoomKitFactory {
        @Override public String category() { return "люкс"; }
        @Override public CosmeticsSet createCosmetics() { return new LuxeCosmetics(); }
        @Override public MinibarContent createMinibar() { return new LuxeMinibar(); }
        @Override public Textile createTextile() { return new LuxeTextile(); }
    }
 
    static class FamilyRoomKitFactory implements RoomKitFactory {
        @Override public String category() { return "сімейний"; }
        @Override public CosmeticsSet createCosmetics() { return new FamilyCosmetics(); }
        @Override public MinibarContent createMinibar() { return new FamilyMinibar(); }
        @Override public Textile createTextile() { return new FamilyTextile(); }
    }
 
    //  Client
 
    /** Знає лише абстракції: жодного if/switch за категорією, жодного new конкретного виробу. */
    static class HousekeepingService {
        private static final String SOURCE = "Служба покоївок";
 
        void prepareRoom(String roomNumber, RoomKitFactory factory) {
            Log.event(SOURCE, "Комплектуємо номер " + roomNumber + ", категорія: " + factory.category());
 
            CosmeticsSet cosmetics = factory.createCosmetics();
            MinibarContent minibar = factory.createMinibar();
            Textile textile = factory.createTextile();
 
            List<RoomItem> kit = List.of(cosmetics, minibar, textile);
            for (RoomItem item : kit) {
                Log.event(SOURCE, "Видано: " + item.describe());
            }
 
            boolean consistent = kit.stream().allMatch(i -> i.category().equals(factory.category()));
            Log.event(SOURCE, "Перевірка комплекту номера " + roomNumber + ": усі елементи належать до категорії «"
                    + factory.category() + "» — " + (consistent ? "так" : "НІ"));
        }
    }
 
    // Розширення: нова категорія «шале» 
    // Лише НОВІ класи; HousekeepingService та інші існуючі класи не змінюються.
 
    static class ChaletCosmetics extends BaseItem implements CosmeticsSet {
        ChaletCosmetics() { super("шале", "Косметика", "натуральне мило, трав'яний шампунь, сауна-набір"); }
    }
    static class ChaletMinibar extends BaseItem implements MinibarContent {
        ChaletMinibar() { super("шале", "Мінібар", "карпатський чай, медові напої, вино (за тарифом «шале»)"); }
    }
    static class ChaletTextile extends BaseItem implements Textile {
        ChaletTextile() { super("шале", "Текстиль", "вовняні пледи, 4 рушники, лляна постіль"); }
    }
    static class ChaletRoomKitFactory implements RoomKitFactory {
        @Override public String category() { return "шале"; }
        @Override public CosmeticsSet createCosmetics() { return new ChaletCosmetics(); }
        @Override public MinibarContent createMinibar() { return new ChaletMinibar(); }
        @Override public Textile createTextile() { return new ChaletTextile(); }
    }
 
    //  Раннер: окремий метод для кожної ситуації 
 
    /** Сценарій 1 і 2: три категорії одним кодом; у журналі видно узгодженість комплектів. */
    static void demoThreeCategories(HousekeepingService service) {
        Log.event("Раннер", "=== Сценарій 1: номери трьох категорій одним кодом служби ===");
        service.prepareRoom("101", new StandardRoomKitFactory());
        service.prepareRoom("201", new LuxeRoomKitFactory());
        service.prepareRoom("301", new FamilyRoomKitFactory());
    }
 
    /** Сценарій 3: нова категорія без змін у коді служби. */
    static void demoNewCategoryChalet(HousekeepingService service) {
        Log.event("Раннер", "=== Сценарій 3: додаємо категорію «шале» новими класами ===");
        service.prepareRoom("Шале-1", new ChaletRoomKitFactory());
    }
 
    public static void main(String[] args) {
        HousekeepingService service = new HousekeepingService();
        demoThreeCategories(service);
        demoNewCategoryChalet(service);
    }
}
