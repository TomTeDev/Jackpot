package jackpot.lotteries;

import jackpot.lotteries.AAConfigs.ConfigMessages;
import jackpot.lotteries.AANew.Placeholders;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class Messages extends ConfigMessages {
    private final Placeholders ph = new Placeholders();
    private final Colors c = new Colors();

    public Messages() {
        super(plugin.getMessagesCfg());
    }
    private final String path_to_wrong_command_message = "path_to_wrong_command_message";
    private final String path_to_wrong_number_message = "path_to_wrong_number_message";
    private final String path_to_not_enough_money_message = "path_to_not_enough_money_message";
    private final String path_to_help_player = "path_to_help_player";
    private final String path_to_help_op = "path_to_help_op";
    private final String path_to_main_inv_title = "path_to_main_inv_title";
    private final String path_to_choose_amount_inv_title = "path_to_choose_amount_inv_title";
    private final String path_to_display_name_madman = "path_to_display_name_madman";
    private final String path_to_display_name_supernova = "path_to_display_name_supernova";
    private final String path_to_display_name_disco = "path_to_display_name_disco";
    private final String path_to_lore_madman = "path_to_lore_madman";
    private final String path_to_lore_disco = "path_to_lore_disco";
    private final String path_to_lore_supernova = "path_to_lore_supernova";
    private final String path_to_accept_button_display_name = "path_to_accept_button_display_name";
    private final String path_to_accept_button_lore = "path_to_accept_button_lore";
    private final String path_to_go_back_button_display_name = "path_to_go_back_button_display_name";
    private final String path_to_go_back_button_lore = "path_to_go_back_button_lore";
    private final String path_to_book_display_name = "path_to_book_display_name";
    private final String path_to_book_lore = "path_to_book_lore";
    private final String path_to_add_small_amount_display_name = "path_to_add_small_amount_display_name";
    private final String path_to_add_medium_amount_display_name = "path_to_add_medium_amount_display_name";
    private final String path_to_add_large_amount_display_name = "path_to_add_large_amount_display_name";
    private final String path_to_add_small_amount_lore = "path_to_add_small_amount_lore";
    private final String path_to_add_medium_amount_lore = "path_to_add_medium_amount_lore";
    private final String path_to_add_large_amount_lore = "path_to_add_large_amount_lore";

    private final String path_to_subtract_small_amount_display_name = "path_to_subtract_small_amount_display_name";
    private final String path_to_subtract_medium_amount_display_name = "path_to_subtract_medium_amount_display_name";
    private final String path_to_subtract_large_amount_display_name = "path_to_subtract_large_amount_display_name";
    private final String path_to_subtract_small_amount_lore = "path_to_subtract_small_amount_lore";
    private final String path_to_subtract_medium_amount_lore = "path_to_subtract_medium_amount_lore";
    private final String path_to_subtract_large_amount_lore = "path_to_subtract_large_amount_lore";
    private final String path_to_add_amount_head_texture = "path_to_add_amount_head_texture";
    private final String path_to_subtract_amount_head_texture = "path_to_subtract_amount_head_texture";
    private final String path_to_lottery_is_not_active_message = "path_to_lottery_is_not_active_message";
    private final String path_to_no_tickets_left_message = "path_to_no_tickets_left_message";
    private final String path_to_message_confirming_player_purchase_tickets = "path_to_message_confirming_player_purchase_tickets";
    private final String path_to_mybets_messages_list = "path_to_mybets_messages_list";

    private List<String> convertList(List<String> list,Player p){
        List<String> newList = new ArrayList<>();
        for(String s:list){
            s = ph.replacePlaceHolders(s,p);
            s = c.translate(s);
            newList.add(s);
        }
        return newList;
    }
    private String convertString(String s,Player p){
        s = ph.replacePlaceHolders(s,p);
        s = c.translate(s);
        return s;
    }
    public String getWrongCommandMessage(){
        String s = getString(path_to_wrong_command_message,null);
        return convertString(s,null);
    }
    public String getWrongNumberMessage(){
        String s = getString(path_to_wrong_number_message,null);
        return convertString(s,null);
    }
    public String getNotEnoughMoney(){
        String s = getString(path_to_not_enough_money_message,null);
        return convertString(s,null);
    }
    public List<String> getPlayerHelpMessages(){
        List<String> list = getListString(path_to_help_player,new ArrayList<>());
        return convertList(list,null);
    }
    public List<String> getOpHelpMessages(){
        List<String> list = getListString(path_to_help_op,new ArrayList<>());
        return convertList(list,null);
    }


    public String getMainInvTitle(){
        String s = getString(path_to_main_inv_title,"Main gui");
        return convertString(s,null);
    }
    public String getChooseAmountGuiTitle(){
        String s = getString(path_to_choose_amount_inv_title,"Choose amount");
        return convertString(s,null);
    }
    public String getDisplayNameMadman(Player p){
        String s = getString(path_to_display_name_madman,"");
        return convertString(s,p);
    }
    public String getDisplayNameDisco(Player p){
        String s = getString(path_to_display_name_disco,"");
        return convertString(s,p);
    }
    public String getDisplayNameSupernova(Player p){
        String s = getString(path_to_display_name_supernova,"");
        return convertString(s,p);
    }
    public List<String> getLoreMadman(Player p){
        List<String> list = getListString(path_to_lore_madman,new ArrayList<>());
        return convertList(list,p);
    }
    public List<String> getLoreDisco(Player p){
        List<String> list = getListString(path_to_lore_disco,new ArrayList<>());
        return convertList(list,p);
    }
    public List<String> getLoreSupernova(Player p){
        List<String> list = getListString(path_to_lore_supernova,new ArrayList<>());
        return convertList(list,p);
    }

    public String getAcceptButtonDisplaName(Player p){
        String s = getString(path_to_accept_button_display_name,"");
        return convertString(s,p);
    }
    public List<String> getAcceptButtonLore(Player p){
        List<String> list = getListString(path_to_accept_button_lore,new ArrayList<>());
        return convertList(list,p);
    }

    public String getGoBackButtonDisplaName(Player p){
        String s = getString(path_to_go_back_button_display_name,"");
        return convertString(s,p);
    }
    public List<String> getGoBackButtonLore(Player p){
        List<String> list = getListString(path_to_go_back_button_lore,new ArrayList<>());
        return convertList(list,p);
    }

    public String getBookDisplaName(Player p){
        String s = getString(path_to_book_display_name,"");
        return convertString(s,p);
    }
    public List<String> getBookButtonLore(Player p){
        List<String> list = getListString(path_to_book_lore,new ArrayList<>());
        return convertList(list,p);
    }

    public String getAddSmallAmountDisplaName(Player p){
        String s = getString(path_to_add_small_amount_display_name,"");
        return convertString(s,p);
    }
    public List<String> getAddSmallAmountLore(Player p){
        List<String> list = getListString(path_to_add_small_amount_lore,new ArrayList<>());
        return convertList(list,p);
    }
    public String getAddMediumAmountDisplaName(Player p){
        String s = getString(path_to_add_medium_amount_display_name,"");
        return convertString(s,p);
    }
    public List<String> getAddMediumAmountLore(Player p){
        List<String> list = getListString(path_to_add_medium_amount_lore,new ArrayList<>());
        return convertList(list,p);
    }
    public String getAddLargeAmountDisplaName(Player p){
        String s = getString(path_to_add_large_amount_display_name,"");
        return convertString(s,p);
    }
    public List<String> getAddLargeAmountLore(Player p){
        List<String> list = getListString(path_to_add_large_amount_lore,new ArrayList<>());
        return convertList(list,p);
    }

    public String getSubtractSmallAmountDisplaName(Player p){
        String s = getString(path_to_subtract_small_amount_display_name,"");
        return convertString(s,p);
    }
    public List<String> getSubtractSmallAmountLore(Player p){
        List<String> list = getListString(path_to_subtract_small_amount_lore,new ArrayList<>());
        return convertList(list,p);
    }
    public String getSubtractMediumAmountDisplaName(Player p){
        String s = getString(path_to_subtract_medium_amount_display_name,"");
        return convertString(s,p);
    }
    public List<String> getSubtractMediumAmountLore(Player p){
        List<String> list = getListString(path_to_subtract_medium_amount_lore,new ArrayList<>());
        return convertList(list,p);
    }
    public String getSubtractLargeAmountDisplaName(Player p){
        String s = getString(path_to_subtract_large_amount_display_name,"");
        return convertString(s,p);
    }
    public List<String> getSubtractLargeAmountLore(Player p){
        List<String> list = getListString(path_to_subtract_large_amount_lore,new ArrayList<>());
        return convertList(list,p);
    }


    public String getAddHeadValue(){
        return getString(path_to_add_amount_head_texture,"");
    }
    public String getSubtractHeadValue(){
        return getString(path_to_subtract_amount_head_texture,"");
    }

    public String getLotteryIsNotActiveNowMessage(){
        String s = getString(path_to_lottery_is_not_active_message,"");
        return convertString(s,null);
    }
    public String getNoTicketsLeftMessage(){
        String s = getString(path_to_no_tickets_left_message,"");
        return convertString(s,null);
    }
    public String getYouBoughtTicketsMessage(){
        String s = getString(path_to_message_confirming_player_purchase_tickets,"");
        return convertString(s,null);
    }
    public List<String> getMybetsMessage(){
        List<String> list = getListString(path_to_mybets_messages_list,new ArrayList<>());
        return convertList(list,null);
    }

}
