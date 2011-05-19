class MainController < ApplicationController
 
  def index    
    @records = TUTwitter.find_records
  end

  # <span class="timestamp">
   # Posted <%= time_ago_in_words(record.dateString) %> ago.
    # </span>
  # add it in index view

   def sports
    @records = TUTwitter.find_sports
  end

  def othernews
    @records = TUTwitter.find_othernews
  end  

 

end
