class TUTwitter < ActiveRecord::Base
  set_table_name "TUTwitter"

  def self.find_records

    find(:all, :conditions => ["dateString > ?", 4.days.ago], :order => 'dateString DESC')
    
  end
    
  
  def self.find_sports
  find(:all, :conditions => { :username => ['TowsonTigers', 'TowsonTigerAD',
        'TowsonMensBball', 'TUCampusRec']} , :order => 'dateString DESC')
  end
#:conditions => , :address => ['']
#:conditions => ['avatar IS NOT NULL']


  def self.find_othernews
    find(:all, :conditions => { :username => ['TheTowerlight', 'TowsonUNews',
        'UBTowsonMBA', 'TowsonSGA', 'TowsonU', 'TUCollofBusEcon', 'TowsonUAlumni',
        'TowsonGlobal', 'TUinCommunity', 'TUOutreach', 'TUBlackboard',
        'tumusic', 'TowsonStudioArt', 'TowsonArtsClctv' ]}, :order => 'dateString DESC', :group => 'dateString')
  end
  
   
end



