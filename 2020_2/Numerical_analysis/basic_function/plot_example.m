% Plot example
x = 0:0.05*pi:2*pi;        % Create x-array
y = sin(x);                % Create y-array
plot(x,y,'k-o')            % Plot x-y points with specified color
                           % (¡¯k¡¯ = black) and symbol (¡¯o¡¯ = circle)
hold on                    % Allows overwriting of current plot
z = cos(x);                % Create z-array
plot(x,z,'k-x')            % Plot x-z points (¡¯x¡¯ = cross)
grid on                    % Display coordinate grid
xlabel('x')                % Display label for x-axis
gtext('sin x')             % Create mouse-movable text (move cross
gtext('cos x')             % hairs to the desired location and
                           % press ¡¯Enter¡¯)